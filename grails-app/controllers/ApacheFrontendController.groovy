class ApacheFrontendController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 100, 1000)
        [apacheFrontendInstanceList: ApacheFrontend.list(params), apacheFrontendInstanceTotal: ApacheFrontend.count()]
    }

    def create = {
        def apacheFrontendInstance = new ApacheFrontend()

        apacheFrontendInstance.properties = params

        return [apacheFrontendInstance: apacheFrontendInstance]
    }

    def save = {
    	
        def apacheFrontendInstance = new ApacheFrontend(params)

        def fqHost = params.name
        
        def firstDot = fqHost.indexOf('.')

        def hostOnly
        
        if (firstDot < 0 )
        	hostOnly = fqHost
        else
        	hostOnly = fqHost.substring(0,firstDot)
       
       def  jkmanagerUrl = new URL("http://" + fqHost + "/jkmanager")
       apacheFrontendInstance.name = hostOnly
       apacheFrontendInstance.jkstatusURL = jkmanagerUrl
       
       def host = Host.findByName(params.name)
       
       def  frontIp
       try {
    	   java.net.InetAddress inetAddr = java.net.InetAddress.getByName(fqHost);
           frontIp = inetAddr.getHostAddress()
       } catch (Exception e) {
    	   log.error e.toString()
    	  flash.message = "hostname $fqHost could not be resolved"
       }
       
       if (!apacheFrontendInstance.save(flush: true)) {
      	   		apacheFrontendInstance.name = fqHost
      	   		render(view: "create", model: [apacheFrontendInstance: apacheFrontendInstance])
       }
       

       if (!host) 
    	   host = new Host(name: fqHost ,ipAddress: frontIp)
        
        
        if (!host.save(flush: true)) {
        	 log.error host.errors
        }
       
        host.addToFrontends(apacheFrontendInstance)     

        
        if (apacheFrontendInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), apacheFrontendInstance.id])}"
            redirect(action: "show", id: apacheFrontendInstance.id)
        }
        else {
        	apacheFrontendInstance.name = fqHost
            render(view: "create", model: [apacheFrontendInstance: apacheFrontendInstance])
        }
    }

    def show = {
        def apacheFrontendInstance = ApacheFrontend.get(params.id)
        if (!apacheFrontendInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), params.id])}"
            redirect(action: "list")
        }
        else {
            [apacheFrontendInstance: apacheFrontendInstance]
        }
    }

    def edit = {
        def apacheFrontendInstance = ApacheFrontend.get(params.id)
        if (!apacheFrontendInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [apacheFrontendInstance: apacheFrontendInstance]
        }
    }

    def update = {
        def apacheFrontendInstance = ApacheFrontend.get(params.id)
        if (apacheFrontendInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (apacheFrontendInstance.version > version) {
                    
                    apacheFrontendInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'apacheFrontend.label', default: 'ApacheFrontend')] as Object[], "Another user has updated this ApacheFrontend while you were editing")
                    render(view: "edit", model: [apacheFrontendInstance: apacheFrontendInstance])
                    return
                }
            }
            apacheFrontendInstance.properties = params
            if (!apacheFrontendInstance.hasErrors() && apacheFrontendInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), apacheFrontendInstance.id])}"
                redirect(action: "show", id: apacheFrontendInstance.id)
            }
            else {
                render(view: "edit", model: [apacheFrontendInstance: apacheFrontendInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def apacheFrontendInstance = ApacheFrontend.get(params.id)
        if (apacheFrontendInstance) {
            try {
                apacheFrontendInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apacheFrontend.label', default: 'ApacheFrontend'), params.id])}"
            redirect(action: "list")
        }
    }
}
