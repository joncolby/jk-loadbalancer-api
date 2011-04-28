import org.codehaus.groovy.grails.commons.*
import groovy.sql.Sql

class CleanupJob {
    //def timeout = 5000l // execute job once in 5 seconds

    def concurrent = false

    def config = ConfigurationHolder.config
    
    def maxMinutes = Integer.parseInt(config.workerCleanupJob.maxValidMinutes)

    def dataSource
    	
    static triggers = {
    	// run every x minutes
       cron name: 'runCleanup', startDelay: 10000, cronExpression: "0 0/10 * * * ?"
       //  cron name: 'runCleanup', startDelay: 10000, cronExpression: "* * * * * ?"
      }
    
    def execute() {
        // execute task
        
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


        
    }
}
