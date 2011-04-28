class ModJkLoadbalancer {
    static hasMany = [workers:ModJkWorker]
    static belongsTo = [frontend:ApacheFrontend]

    String name

     String toString() {
        "${name}"
    }
    
	  static mapping = {
		  cache true
	      version false
	  }
    
//   static fetchMode = [workers: 'eager']
}
