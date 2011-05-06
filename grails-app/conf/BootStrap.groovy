
class BootStrap {

	 def JkService jkService

     def init = { servletContext ->


     // cleanout all hosts on startup
     Host.list().each { it.delete(flush:true) }

     // add non-autoconf servers manually

    def front38_1a = new ApacheFrontend (name:"front38-1.mobile.rz", jkstatusURL:"http://front38-1.mobile.rz/jkmanager")
    def front38_1 = new Host (name:"front38-1.mobile.rz",ipAddress:"10.38.252.41")
    front38_1.addToFrontends(front38_1a)
    front38_1.save(flush:true)

    def front38_2a = new ApacheFrontend (name:"front38-2.mobile.rz", jkstatusURL:"http://front38-2.mobile.rz/jkmanager")
    def front38_2 = new Host (name:"front38-2.mobile.rz",ipAddress:"10.38.252.42")
    front38_2.addToFrontends(front38_2a)
    front38_2.save(flush:true)


     
   // bootstrapping front hosts should only be done when database is initialized!  
   // otherwise you get duplicate front hosts
   
/*
    (1..36).each { i ->
        

        def frontend = new ApacheFrontend (
                 name:"front45-${i}.mobile.rz",
                 jkstatusURL:"http://front45-${i}.mobile.rz/jkmanager")
                 //jkstatusURL:"http://10.45.74.${i}/jkmanager")

             def host = new Host (
                 name:"front45-${i}.mobile.rz",
                 ipAddress:"10.45.74.${i}")

            host.addToFrontends(frontend)
            if(!host.save())
                println host.errors
                
                
                frontend = new ApacheFrontend (
                        name:"front46-${i}.mobile.rz",
                        jkstatusURL:"http://front46-${i}.mobile.rz/jkmanager" )
                        //jkstatusURL:"http://10.46.74.${i}/jkmanager" )
                   
                 host = new Host (
                        name:"front46-${i}.mobile.rz",
                        ipAddress:"10.46.74.${i}")

                   host.addToFrontends(frontend)
                   if(!host.save())
                       println host.errors

                
         }
*/

// KFRONT 
/*
    (1..2).each { i ->
    

    def frontend = new ApacheFrontend (
             name:"kfront45-${i}.mobile.rz",
             jkstatusURL:"http://front45-${i}.mobile.rz/jkmanager")


         def host = new Host (
             name:"kfront45-${i}.mobile.rz",
             ipAddress:"10.45.74.10${i}")

        host.addToFrontends(frontend)
        if(!host.save())
            println host.errors
            
            
            frontend = new ApacheFrontend (
                    name:"kfront46-${i}.mobile.rz",
                    jkstatusURL:"http://kfront46-${i}.mobile.rz/jkmanager" )

               
             host = new Host (
                    name:"kfront46-${i}.mobile.rz",
                    ipAddress:"10.46.74.10${i}")

               host.addToFrontends(frontend)
               if(!host.save())
                   println host.errors

            
     }
*/
   
   
 /*
        def frontend = new ApacheFrontend (
                         name:"localhost",
                         jkstatusURL:"http://127.0.0.1/jkmanager" )
         def host = new Host (
             name:"localhost",
             ipAddress:"127.0.0.1")

        host.addToFrontends(frontend)
        if(!host.save())
            println host.errors
 */
 
         
         
    }
   
     
     def destroy = {
     }
} 