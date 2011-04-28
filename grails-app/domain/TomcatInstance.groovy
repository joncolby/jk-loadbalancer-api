class TomcatInstance {
    static belongsTo = [host:Host]
    // Workers sollen nicht gel�scht werden wenn instance gel�scht wird?
    static hasMany = [ workers: ModJkWorker, webartifacts: Webartifact ]
 /*   static constraints = {
        name(validator: { val, obj ->
            return null == TomcatInstance.findByNameAndHost(val,obj.host)
        })
    }
    */
    static mapping = {
      columns {
         release column:'release_tag'
      }
   }
    
    String name
    String absolutePath
    String portPrefix
    String release
    String status

    String toString() {
        "${name} : ${release}"
    }
}
