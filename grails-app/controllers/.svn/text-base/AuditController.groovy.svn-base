
class AuditController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
    	
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [auditInstanceList: Audit.list(params), auditInstanceTotal: Audit.count()]
    }


    def show = {
        def auditInstance = Audit.get(params.id)
        if (!auditInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'audit.label', default: 'Audit'), params.id])}"
            redirect(action: "list")
        }
        else {
            [auditInstance: auditInstance]
        }
    }



}
