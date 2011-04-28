class Host {
    // static hasMany = [ instances: TomcatInstance, frontends: ApacheFrontend ]
    static hasMany = [ frontends: ApacheFrontend ]
    String name
    String ipAddress

    String toString() {
        "${name}"
    }
    
	  static mapping = {
		  cache true
	      version false
	  }
    
    
}
