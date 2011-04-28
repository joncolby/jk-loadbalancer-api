class ApacheFrontend {
    static belongsTo = [host:Host]
    static hasMany = [ loadbalancer: ModJkLoadbalancer ]
    static constraints = {
        name(unique:true,nullable:false, blank:false )
         
        // url validator doesnt seem to work when resaving
        //jkstatusURL( nullable:false, url:true, blank:false )
        jkstatusURL( nullable:false, blank:false )
        user( nullable:true )
        password( nullable:true )
        jkversion( nullable:true, blank:true )
    }
    String name
    String jkstatusURL
    String jkversion
    String user
    String password
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

//   static mapping = {
//     autoTimestamp false
//   }

	  static mapping = {
		//cache false
		version false
	  }
    
    
 //   static fetchMode = [loadbalancer: 'eager']
    
    String toString() {
        "${name}"
    }
    
}
