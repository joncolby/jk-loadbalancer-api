class HostController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 100, 1000)
        [hostInstanceList: Host.list(params), hostInstanceTotal: Host.count()]
    }

    def create = {
        def hostInstance = new Host()
        hostInstance.properties = params
        return [hostInstance: hostInstance]
    }

    def save = {
        def hostInstance = new Host(params)
        if (hostInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'host.label', default: 'Host'), hostInstance.id])}"
            redirect(action: "show", id: hostInstance.id)
        }
        else {
            render(view: "create", model: [hostInstance: hostInstance])
        }
    }

    def show = {
        def hostInstance = Host.get(params.id)
        if (!hostInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'host.label', default: 'Host'), params.id])}"
            redirect(action: "list")
        }
        else {
            [hostInstance: hostInstance]
        }
    }

    def edit = {
        def hostInstance = Host.get(params.id)
        if (!hostInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'host.label', default: 'Host'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [hostInstance: hostInstance]
        }
    }

    def update = {
        def hostInstance = Host.get(params.id)
        if (hostInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (hostInstance.version > version) {
                    
                    hostInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'host.label', default: 'Host')] as Object[], "Another user has updated this Host while you were editing")
                    render(view: "edit", model: [hostInstance: hostInstance])
                    return
                }
            }
            hostInstance.properties = params
            if (!hostInstance.hasErrors() && hostInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'host.label', default: 'Host'), hostInstance.id])}"
                redirect(action: "show", id: hostInstance.id)
            }
            else {
                render(view: "edit", model: [hostInstance: hostInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'host.label', default: 'Host'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def hostInstance = Host.get(params.id)
        if (hostInstance) {
            try {
                hostInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'host.label', default: 'Host'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'host.label', default: 'Host'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'host.label', default: 'Host'), params.id])}"
            redirect(action: "list")
        }
    }
}
