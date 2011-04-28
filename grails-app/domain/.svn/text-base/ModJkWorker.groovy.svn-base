class ModJkWorker {
    static belongsTo = [loadbalancer:ModJkLoadbalancer]
    static hasMany = [statistics:ModJkWorkerStatistic]
    static constraints = {
       port( range: 1..66560 )
       state( inList: ['NONE', 'OK/IDLE', 'OK', 'ERR', 'ERR/BUSY', 'ERR/PRB', 'ERR/FRC', 'ERR/REC'] )
       activation(  inList: ['NONE', 'ACT', 'DIS', 'STP'] )
       // instance(nullable:true)
       //queue(nullable:true)

    }

    // TomcatInstance instance
    
    //ActivationQueue queue
    
    
    String name
    String route
    String host
    String address
    Integer port
    String state
    String activation
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

	  static mapping = {
    	autoTimestamp false
		cache false
	    version false
	  }

     String toString() {
        "${name} : ${state} ${activation}"
    }
    
}
