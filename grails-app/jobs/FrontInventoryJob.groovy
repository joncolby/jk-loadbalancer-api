import groovy.sql.Sql
import org.codehaus.groovy.grails.commons.*

class FrontInventoryJob {
    //def timeout = 5000l // execute job once in 5 seconds

      static triggers = {
        simple name:'simpleTrigger', startDelay:10000, repeatCount: 1
    	cron name:'FrontInventoryJob',  cronExpression: '0 0/5 * * * ?'
		 //cron name:'FrontInventoryJob',  cronExpression: '* * * * * ?'

     }

    def concurrent = false

    def config = ConfigurationHolder.config

    def inventoryDBUrl = config.inventoryDBUrl
    def inventoryDBUser  = config.inventoryDBUser
    def inventoryDBPassword = config.inventoryDBPassword
    def maxFronts = Integer.parseInt(config.inventory.maxScanFronts)
    def dbFronts = []

    def execute() {
        // execute task

      log.info "Starting Mod-JK Inventory Job"


      // read fronts out of the inventory database
      def sql = Sql.newInstance(inventoryDBUrl, inventoryDBUser, inventoryDBPassword, "com.mysql.jdbc.Driver")

      def frontClassIds = []

      sql.eachRow("select serverclass_id from serverclass where classname like '%FRONT%' or classname = 'FFIRE' or classname = 'FEBAYK'") {
          frontClassIds << it.serverclass_id
      }


      frontClassIds.each { serverclass_id ->

          log.debug "serverclass_id is " + serverclass_id

          sql.eachRow("select get_hostname_by_server_id(server_id) as name from assignment where serverclass_id=${serverclass_id}", {

              log.debug "found server " + it.name
              dbFronts << it.name

                })

      }


      sql.close()


      dbFronts.each {

            def host = "${it}.mobile.rz"

            def c =  ApacheFrontend.findByName(host)

            if ( ! c )  {
                           try {
                                    InetAddress address = InetAddress.getByName(host)
                                    def ip = address.getHostAddress()

                                def h = Host.findByName(host)

                                if ( ! h )   {
                                      h = new Host (name:host,ipAddress:ip)

                                } else {
                                      h.ipAddress = ip
                                }

                                    if( h.save(flush:true) ) {
                                      // continue to create and save apachefront
                                      def af = new ApacheFrontend (
                                                    name: host,
                                                    jkstatusURL:"http://$host/jkmanager")

                                        h.addToFrontends(af)

                                        if(!h.save())
                                        log.error h.errors


                                    }


                              } catch (UnknownHostException uhe) {
                                    log.error "$host cannot be resolved"
                              }

            }

      }

      /**
      datacenters.each {  dc ->

            for (i in 1..maxFronts) {

              hostprefixes.each { prefix ->
                        log.info "checking if host $prefix$dc-$i exists"


                def host = prefix + dc + "-" + i + ".mobile.rz"

                try {

                  InetAddress Address = InetAddress.getByName(host)

                  if ( Address.isReachable(2000) ) {
                    log.debug "$host is reachable"

                    def savedFront = ApacheFrontend.findByName(host)

                    def ip = Address.getHostAddress()


                     if ( savedFront )   {
                                log.info "$host already exists. updating ip address."
                                savedFront.host.ipAddress = ip
                     } else  {

                                log.info "new front server detected: $host"


                                def newHost = new Host (
                                name:host,
                                ipAddress:ip)

                                newHost.save(flush:true)

                                def newFront = new ApacheFrontend (
                                name:host,
                                jkstatusURL:"http://$host/jkmanager")


                                newHost.addToFrontends(newFront)

                                if(!newHost.save())
                                    log.error newHost.errors


                    }



                  }
                  
                } catch (UnknownHostException uhe) {
                    log.error "$host cannot be resolved"
                }




              }



            }


      }

      **/


    }
}
