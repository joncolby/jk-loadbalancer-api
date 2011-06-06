import org.apache.jk.status.JkStatus
import org.apache.jk.status.JkBalancerMember

import org.hibernate.FetchMode as FM
import groovy.sql.Sql

import org.apache.http.conn.ConnectTimeoutException;

import org.codehaus.groovy.grails.commons.*

import org.gparallelizer.*

/**
 * Scan all Apache frontend mod_jk loadbalancer
 * Todo: Add Testcases
 * Todo: Is a service a better way to implement this?

 */
class ApacheFrontendScannerJob{
  
    static triggers = {
//    	simple name:'simpleTrigger', startDelay:1000, repeatCount: 1
//    	cron name:'cronTrigger',  cronExpression: '0 0/1 * * * ?'
		cron name:'cronTrigger',  cronExpression: '* * * * * ?'

     }
    
    
    def config = ConfigurationHolder.config
    
    def sessionRequired = true
    //def sessionRequired = false

    def int count = 9
    def int processUpdateFrequency = 10
    def concurrent = false
    def JkService jkService
    def concurrentScans = Integer.parseInt(config.frontscanner.parallelThreads)
    //def concurrentScans = 10
    def sessionFactory

    def dataSource
    
    def frontlist

    def maxMinutes = Integer.parseInt(config.workerCleanupJob.maxValidMinutes)
    
   def execute() {
    	
    	//sessionFactory.currentSession.flush()
    	
    	log.info "starting quartz job at " + new Date()
    	
        count = (count + 1) % processUpdateFrequency

        frontlist = ApacheFrontend.list(max:concurrentScans,sort:"lastUpdated",order:"asc")
        
        // DEBUG
        //frontlist = ApacheFrontend.getAll( [5,10,61,62] )
       
        List jkstatuses
        
        Parallelizer.withParallelizer(concurrentScans) {
    	  
        	jkstatuses = frontlist.collectAsync { frontend ->
        	JkStatus jkstatus
        		try {
        		log.info "starting data collection for " + frontend.name + " "  + new Date()
        		jkstatus = jkService.jkStatus(frontend.jkstatusURL,frontend.user,frontend.password)
        	    log.info "finished data collection for " + frontend.name + " "  + new Date()
        		
        		} catch ( ConnectTimeoutException cex ) {
        			 log.error "Cannot connect to $frontend.name"
        			 return null
                } catch (ConnectException ce) {
                     log.error "Connection refused to " + frontend.name + " : " + ce.toString()
                     //def bad = Host.findByName(frontend.name)
                     //bad.delete()
                     return null
                } catch (java.net.UnknownHostException uhe) {
                     //log.error "Unknown host " + frontend.name + " (assuming host was deconfigured. deleting this host) : " + uhe.toString()
                     log.error "Unknown host " + frontend.name + " : " + uhe.toString()
                     //def bad = Host.findByName(frontend.name)
                     //bad.delete()
                     return null
        		} catch (Exception e) {
                    log.error "uncaught exception encountered: " + e.toString()
        			return null
        		} finally {
        			log.debug "updating timestamp for " + frontend.name
            		def sql = new Sql(dataSource)
            		sql.executeUpdate("update apache_frontend set last_updated = ? where id = ?",[new Date(),frontend.id])
                    sql.close()
        		}
	
        		return jkstatus
    	  }

      }
       
 


  	jkstatuses.each { jkstatus ->

		if (jkstatus)  {
  			
  		def name = jkstatus.server.name

  		def frontend = ApacheFrontend.findByNameLike(name)

  		log.info 'Analyzing JK Status on ' + frontend.name

        frontend.jkversion =  jkstatus.software.jk_version

        if (  ! frontend.save()  ) {
        		log.error "problem saving front "
        			frontend.errors.allErrors.each {
        			log.error it
        			}
        }



                jkstatus.balancers.each{ balancer ->
                
                log.info "saving info for ${frontend.name} ${balancer.name}"
                
                def lb = ModJkLoadbalancer.findByFrontendAndName(frontend,balancer.name)

                
                if(lb == null) {
                    lb = new ModJkLoadbalancer(name:balancer.name)
                    lb.frontend = frontend

                    lb.save()
                } 
                // else { // Update Attributes }
                def  members = balancer.members
                members.each{ JkBalancerMember member ->
                
                	if ( member.host =~ /\./)
                		member.host = member.host.split(/\./)[0]

                    //ModJkWorker worker = ModJkWorker.findByLoadbalancerAndName(lb,member.name,[lock:true])
                    ModJkWorker worker = ModJkWorker.findByLoadbalancerAndName(lb,member.name)
                    
                    if(worker == null) {
                        worker = new ModJkWorker(
                                name:member.name,
                                route:member.route,
                                host:member.host,
                                address:member.address,
                                port:member.port,
                                activation:member.activation,
                                state:member.state
                        )
                        worker.loadbalancer = lb
                        // TODO
                        /*
                        TomcatInstance instance = TomcatInstance.findByName(member.route)
                        worker.instance = instance
                        */

                    } else {
                    	
                         worker.route=member.route
                         worker.host=member.host
                         worker.address=member.address
                         worker.port=member.port
                         worker.activation=member.activation
                         worker.state=member.state

                    	// TODO
                         /*
                         if(worker.instance == null || member.route != worker.instance?.name) {
                            TomcatInstance instance = TomcatInstance.findByName(member.route)
                            worker.instance = instance
                         }
                         */
                    }
                    
                 
   /* NO STATISTICS LOGGING -- slows down job, especially when table is big */                 
 /*
                    if (count == 0) {
                        def ModJkWorkerStatistic stats = new ModJkWorkerStatistic()
                        stats.errorCounter = member.errors
                        stats.accessCounter = member.elected
                        stats.clientErrors = member.client_errors
                        stats.readBytes = member.readed
                        stats.writeBytes = member.transferred
                        stats.busyConnections = member.busy
                        stats.worker = worker
                        worker.addToStatistics(stats)
                        //stats.save()
                    }
*/


                    //worker.save(flush:true)
                    
                            if (  ! worker.save()  ) {
                            		log.error "problem saving worker "
                            			worker.errors.allErrors.each {
                            				log.error it
                            			}
                            }
                    
					// force timestamp update
					def sql = new Sql(dataSource)

					sql.executeUpdate("update mod_jk_worker set last_updated = ? where id = ?",[new Date(),worker.id])

                    sql.close()
                  
                    log.debug "${frontend.name} - ${member.name} - ${member.route}"

                }
                
            } // loop through balancers

        } // if jkstatus is not null
            
        } // loop through list of frontends

    // sessionFactory.currentSession.clear()
    // sessionFactory.currentSession.flush()


     // CLEANUP MAINTENANCE TASK
        def now = new Date()
        log.info "running cleanup job at " + now

        def c = Calendar.getInstance()
        c.add(Calendar.MINUTE, -maxMinutes)
        def oldAge = c.getTime()
        log.info "looking for workers older than " + oldAge
        def results = ModJkWorker.findAllByLastUpdatedLessThanEquals(oldAge)

        results.each { worker ->
        	log.info "deleting old entry $worker with last modified date " + worker.lastUpdated
        	worker.delete()
        }


    } // close execute
} // close class
