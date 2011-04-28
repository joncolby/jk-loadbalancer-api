
class ActivationQueue {
    
    static constraints = {
        newActivation(nullable:true)
        newActivationTime(nullable:true)
     }
	
	
	//static belongsTo = [worker: ModJkWorker]

    String workerId
    
	String newActivation
	Date newActivationTime

    Date dateCreated = new Date()
    Date lastUpdated = new Date()
    
    String toString() {
        "worker_id: ${workerId}, new_activation: ${newActivation}, new_activation_time:  ${newActivationTime}, created: ${dateCreated}"
    }
    
    
}
