
class Audit {

	String ipAddress
	String userAgent
	String target
	String command
	String result
    Date dateCreated = new Date()
	
    static constraints = {
		result(inList: ['SUCCESS', 'ERROR'] )
		ipAddress(nullable:false, blank:false )
        target( nullable:false, blank:false)
        command( nullable:false, blank:false )
    }
}
