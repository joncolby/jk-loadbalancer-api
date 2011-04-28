import org.codehaus.groovy.grails.commons.*

class RotateAuditJob {
	//  def timeout = 5000l // execute job once in 5 seconds

    def concurrent = false
    def config = ConfigurationHolder.config
    
    def daysToKeep = Integer.parseInt(config.audit.daysToKeep)
    
    static triggers = {
    	// run every x minutes
       cron name: 'runAuditRotate', startDelay: 10000, cronExpression: "0 0 10 * * ?"
       //cron name: 'runAuditRotate', startDelay: 10000, cronExpression: "* * * * * ?"
      }
    
    
    
    def execute() {
        // execute task
        
        def now = new Date()
        log.info "running audit cleanup job at " + now
        
        def c = Calendar.getInstance()
        c.add(Calendar.DATE, - daysToKeep)
        def oldAge = c.getTime()
        def results = Audit.findAllBydateCreatedLessThanEquals(oldAge)
        
        results.each { entry ->
        	log.info "deleting audit entry $entry.id with last creation date " + entry.dateCreated
        	entry.delete()
        }
        
        
    }
}