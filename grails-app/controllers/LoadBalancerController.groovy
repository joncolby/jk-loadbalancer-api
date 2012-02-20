import org.hibernate.FetchMode as FM
import grails.converters.*
import groovy.xml.MarkupBuilder
import static org.apache.commons.lang.StringUtils.*


class LoadBalancerController {

	  boolean transactional = false
      
	  def JkService jkService
	  def JkPingService JkPingService

      //  def index = { redirect(action:'list',params:params) }
	  
	  def index = { 
			  	//render "missing rest action."
	  	}

        // the delete, save and update actions only accept POST requests
        static def allowedMethods = [delete:'POST', save:'POST', update:'POST']

	  def ping = {
		  
          //  if no host parameter, then do not go any further 
          if (! params.host || params.host == "" ) {
          	render "missing host parameter"
          	return
          }
          
          params.route = params.instance
          
          def ports = []
          
          def workers = jkService.searchWorkers(params.route, params.host, params.activation, params.state)
          
          if ( !workers ) {
        	        	  render "ERROR - no workers found"
        	        	  return false
          }
          
                    
          ports = workers.collect { w -> w.port }
          
          ports = ports.unique().sort()
          
          ports.each { port ->
        	  	if ( JkPingService.ping("$params.host",port) ) {
        	  		render "OK - $params.host $port\n"
        	  	} else {
        	  		render "ERR - $params.host $port\n"
        	  	}
          }
          
	  }
	  
    def updateActivationWorker = {
        	
		  // default sleeptime between activations is 0
		  def maximum = 0
		  
		  try {
			  	if ( params.slow && Integer.parseInt(params.slow) )
				  				maximum = params.slow	
		  } catch (NumberFormatException nfe) {
			  	log.error nfe.toString()
		  }
		  

		  params.route = params.instance
        	params.updateActivation = upperCase(params.operation)
            def  workers

            //  if no host parameter, then do not go any further 
            if (! params.host || params.host == "" ) {
            	render "ERROR - missing host parameter"
            	return false
            }

            if (params.updateActivation) {
                workers = jkService.searchWorkers(params.route, params.host, params.activation, params.state)

                log.debug "WORKERS FOUND FOR ${params.host}: ${workers}"

                //  update workers in background thread. update status in DB

                		workers.each { ModJkWorker worker ->

                		        String url = worker.loadbalancer.frontend.jkstatusURL
                		        String lbname = worker.loadbalancer.name
                  			    String name =  worker.name
                  				String jkversion = worker.loadbalancer.frontend.jkversion
                  				String activation = params.updateActivation
 
                  				// if no slow given, save new activation right away so user sees new activation
                  				if ( maximum == 0 )
                  					worker.activation = params.updateActivation
                  				
	                   	   		def minimum = 0
                    	   		def random = new Random()
                		        def sleeptime
                		        
                		        // jkService.updateActivation(worker, params.updateActivation)
                		        
                		        sleeptime = random.nextInt(maximum.toInteger()-minimum+1)+minimum
                		        
                		         def c = Calendar.getInstance()
                		         c.add(Calendar.SECOND, sleeptime)
                		         def activationTime = c.getTime()

                		        log.debug "setting sleep time for worker $worker to $activationTime (+$sleeptime seconds)"

                		        def queue = new ActivationQueue(workerId: worker.id, newActivation: activation, newActivationTime:activationTime)
                				
                				queue.save(flush:true)
                				
                		        //worker.queue = queue
               		        
                		       if (! worker.save(flush:true) ) {
                		    	   log.error "problem saving worker"
                		    	   			worker.errors.allErrors.each {
                		    		   				log.error it
                       					}
                		       }
                		    	   

                		}

                
                // log to audit table
                def result
                
                	if (!workers) {
                		result = 'ERROR'
                		def error = "ERROR - no workers found"
						log.error(error)
						render error
						return false
                	} else {
                		result = 'SUCCESS'
                	}
                
    		def userIp = request.getHeader("X-Forwarded-For") ? request.getHeader("X-Forwarded-For") : request.remoteAddr
    		def queryStr = request.getQueryString() ? "?" + request.getQueryString() :  ""
			
			// log to audit table
			def audit = new Audit(ipAddress: userIp,
					userAgent: request.getHeader("User-Agent"),
					target: params.host,
					command: request.forwardURI + queryStr,
					result: result
			)
			
			if (audit.save())
					log.debug "saving audit event " + audit
			else {
				   log.error("could not create audit event for ip " + userIp)
						audit.errors.allErrors.each {
					   			log.error it
				   		}	   
			}
						

                /* ORIGINAL
                   workers.each { ModJkWorker worker ->
              	 			jkService.updateActivation(worker, params.updateActivation)
                	   		worker.activation = params.updateActivation
                	   		worker.save(flush:true)
                	   		println worker

                }
               */
               
           }
            
            // PRINT USING LIST VIEW TEMPLATE
       /*     
            render(view: 'list', model: [modJkWorkerList: workers,
                    route: params.route,
                    host: params.host,
                    activation: params.activation,
                    state: params.state,
                    updateActivation: params.updateActivation])
          */          
                    
                    render(view:'text', model: [modJkWorkerList: workers,
                                                route:params.route,
                                                host:params.host,
                                                activation:params.activation,
                                                state:params.state])

                    
                    
        }

        

        
        def search = {

         params.route = params.instance
         
          def _workers =  jkService.searchWorkers(params.route,params.host,params.activation,params.state)
          
          	if (!_workers)
                		render "ERROR - no workers found"
   
/*
          render(contentType:"text/xml") {
    
        	 	host(name: params.host) {
        		 		for(w in _workers) {
        		 		instance(name: w.name,
        		 						front:w.loadbalancer.frontend.name,
        		 						activation:w.activation,
        		 						state:w.state,
        		 						address: w.address,
        		 						port: w.port
        		 						)
        		 		}
        	 	}
         }
*/          

           render(view:'text', model: [modJkWorkerList:_workers,
                    route:params.route,
                    host:params.host,
                    activation:params.activation,
                    state:params.state])

           
        }
	  
	  
	  
      def listlb = {

	         def lbname = params.lbname
	         def lb = null 
	         
	         if (!lbname) {

	        	 lb = ModJkLoadbalancer.executeQuery( "select distinct name from ModJkLoadbalancer")

	        	 render(view:'listlb_text', model: [loadbalancers: lb])
	        	 return true
	        	   
	         } else {
	        	 lb = ModJkLoadbalancer.findAllByName(lbname)

               	 render(view:'listlb_lbname_text', model: [loadbalancers: lb])
	        	 return true
	         }
	         
	         
	         
	         if (!lb) {
	           		render "ERROR - lbname not found"
	           		return false
	         }
	         
	         def _workers = lb.workers

	          	if (!_workers) {
	                		render "ERROR - no workers found"
	                		return false
	          	}
	   
	           render(view:'text', model: [modJkWorkerList:_workers,
	                    route:params.route,
	                    host:params.host,
	                    activation:params.activation,
	                    state:params.state])

	           
	        }
        


        def list = {
            //if(!params.max) params.max = 10

            [ modJkWorkerList: ModJkWorker.list( params ) ]
        }

        

        def show = {
        		
        	def modJkWorker =  ModJkWorker.findAllByHost(params.host)
            //def modJkWorker = ModJkWorker.get( params.id )

            if(!modJkWorker) {
                flash.message = "ERROR - Host not found: ${params.host}"
                render flash.message
                // redirect(action:list)
            }
            else {
            	render modJkWorker as XML
            //	return [ modJkWorker : modJkWorker ] 
            	}
        	
        }



        def delete = {
            def modJkWorker = ModJkWorker.get( params.id )
            if(modJkWorker) {
                modJkWorker.delete()
                flash.message = "ModJkWorker ${params.id} deleted"
                redirect(action:list)
            }
            else {
                flash.message = "ModJkWorker not found with id ${params.id}"
                redirect(action:list)
            }
        }

        def edit = {
            def modJkWorker = ModJkWorker.get( params.id )

            if(!modJkWorker) {
                flash.message = "ModJkWorker not found with id ${params.id}"
                redirect(action:list)
            }
            else {
                return [ modJkWorker : modJkWorker ]
            }
        }

        def update = {
            ModJkWorker modJkWorker = ModJkWorker.get( params.id )
            if(modJkWorker) {
                def activation = params.activation
                if(!activation.equals(modJkWorker.activation)) {
                    jkService.updateActivation(modJkWorker, activation)
                }
                // Only route and activation allowed
                // if Route change instance also Change!!
                // check jvmROute at instance and port/address
                modJkWorker.properties = params
                if(!modJkWorker.hasErrors() && modJkWorker.save()) {
                    flash.message = "ModJkWorker ${params.id} updated"
                    redirect(action:show,id:modJkWorker.id)
                }
                else {
                    render(view:'edit',model:[modJkWorker:modJkWorker])
                }
            }
            else {
                flash.message = "ModJkWorker not found with id ${params.id}"
                redirect(action:edit,id:params.id)
            }
        }

        def create = {
            def modJkWorker = new ModJkWorker()
            modJkWorker.properties = params
            return ['modJkWorker':modJkWorker]
        }

        def save = {
            def modJkWorker = new ModJkWorker(params)
            
            if(!modJkWorker.hasErrors() && modJkWorker.save()) {
                flash.message = "ModJkWorker ${modJkWorker.id} created"
                redirect(action:show,id:modJkWorker.id)
            }
            else {
                render(view:'create',model:[modJkWorker:modJkWorker])
            }
        }


}
