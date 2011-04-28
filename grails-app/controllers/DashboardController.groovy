import java.util.Calendar

class DashboardController {

    def index = {     }
    

    def workerErrors = {
    		
    		if (!params.max) params.max = 20
    		if (!params.sort) params.sort = 'loadbalancer'
    		
    		def result = ModJkWorker.createCriteria()
    		
    		/*
    		def c = Calendar.getInstance()
    		def now = c.getTime()
    		c.add(Calendar.MINUTE, -20)
       		def minutesBefore = c.getTime()

    		def notOKs = result.list(max: 100) {
    					and {
    							//between('lastUpdated',minutesBefore,now)
    							like('state','%ERR%')
    					}
    		}
    		*/

  		def workers = ModJkWorker.findAllByStateLike('%ERR%', params)
    	def total = ModJkWorker.countByStateLike('%ERR%')
    	
   		return [ notOKList: workers, notOKTotal: total  ] 
    		
    }
    
    def workerActivations = {
    	
    		if (!params.max) params.max = 20
    		if (!params.sort) params.sort = 'loadbalancer'
 
     		def workers = ModJkWorker.findAllByActivationNot('ACT',  params )
    		def total = ModJkWorker.countByActivationNot('ACT')

    		return [ notActivesList: workers, notActivesTotal: total  ] 
    		
    }
    
    def activationQueue = {
    		def items = ActivationQueue.list(params)
    		def total = ActivationQueue.count()

    		return [ items:items, total: total ]
    }
    
    
}
