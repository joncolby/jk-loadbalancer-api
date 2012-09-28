import org.apache.jk.status.JkStatus
import org.apache.jk.status.JkBalancerMember
import org.apache.jk.status.JkStatusAccessor
import org.hibernate.FetchMode as FM


class JkService {

    //boolean transactional = true
    boolean transactional = false

    def boolean updateActivation(ModJkWorker jkWorker, String activation) {

        boolean status = false
        def loadbalancer = jkWorker.loadbalancer.name
        def worker = jkWorker.name
        def url
        def jkversion =  jkWorker.loadbalancer.frontend.jkversion

        //      difference in URL with mod_jk >= 1.2.27
        if (jkversion =~ /1.2.26/ ) {
        	url = "${jkWorker.loadbalancer.frontend.jkstatusURL}?cmd=update&mime=txt&w=${loadbalancer}&sw=${worker}&wa=${activation}"
        } else {
        	url = "${jkWorker.loadbalancer.frontend.jkstatusURL}?cmd=update&mime=txt&w=${loadbalancer}&sw=${worker}&vwa=${activation}"
        }

        def conn
        
        
        try {
        	
        	// println url
            conn = new URL(url).openConnection()
            conn.requestMethod = "GET"
            conn.doOutput = true

           // def response = conn.inputStream.getText()
            def response = conn.inputStream.getText().replaceAll(/\n$/,'')

            def responseCode = conn.responseCode
            
            log.info "Got response [$responseCode] $response from $url"
            
            if ( responseCode == conn.HTTP_OK &&  response.startsWith('Result: type=OK')) {
                status = true
            } else {
            	log.error "bad response [$responseCode] from $url: "
            }
        	
        } finally {
            if(conn) conn.disconnect()
        }

        return status
        
        
    } 

    

    def searchWorkers(String route, String host, String activation, String state) {
    	
        // get host name only
        if ( host =~ /\./)
    		host = host.split(/\./)[0]
    	
         def c = ModJkWorker.createCriteria()
         return c.list() {
              and {
                  1 == 1
                  if(activation && activation != 'NONE')
                    eq('activation', activation)
                  if(state && state != 'NONE')
                    like('state', "${state}%")
                  if(route) {
                     def i = route.indexOf(',')
                     if(i == -1) {
                        like('route',"${route}%")
                     } else {
                        or {
                             for(line in route.split(",")) {
                                 like('route',"${line}%")
                             }
                        }
                     }
                  }
                  if(host) {
                 //   like('host',"%${host}%")
                    like('host',"${host}")
                  }
              }
              fetchMode('loadbalancer', FM.EAGER)
              order('name', 'asc')
        }
    }

    def searchLoadBalancer(String lbName, String activation, String state, String netmask = null) {

        def c = ModJkWorker.createCriteria()

        def results = c.list() {
            and {
                loadbalancer {
                    eq('name', lbName)
                }
                if (activation)
                    eq('activation', activation)
                if (state)
                    ilike('state', "${state}%")
                if (netmask)
                    like('address', "${netmask}%")
            }
            fetchMode( 'loadbalancer', FM.EAGER)

            projections {
		        countDistinct "name"
	        }
        }

        if (results)
            return results[0]
        else
            return null

    }

    def JkStatus jkStatus(String url, String user, String password) {
        def statusAccessor = new JkStatusAccessor()
        JkStatus jkstatus = null
        // Todo Handling Exception - IOE or Timeout
        try {
            jkstatus = statusAccessor.status(url, user, password)
        } catch (Exception ce) {
            log.error "Service ${url} not available"
            log.error "URL " + url + " : " + ce.toString()

            throw ce
        }
        return jkstatus
    }
    
    

    
}
