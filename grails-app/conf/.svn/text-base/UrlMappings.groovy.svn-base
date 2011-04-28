class UrlMappings {
    static mappings = {

      
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      
	  "/service/$rest/$domain/$id?"{
            controller = "rest"
            action = [GET:"show", PUT:"create", POST:"update", DELETE:"delete"]
            constraints {
                rest(inList:["rest","json"])
            }
       }


	  "/list/$host?"{
          controller = "loadBalancer"
          //action = [GET:"search",PUT:"create", POST:"update", DELETE:"delete"]
          action = "search"

     }

	"/listlb/$lbname?"{
          controller = "loadBalancer"
          //action = [GET:"search",PUT:"create", POST:"update", DELETE:"delete"]
          action = "listlb"

     }

	  "/ping/$host?"{
          controller = "loadBalancer"
          action = "ping"

     }

	  "/$operation/$host?"{
          controller = "loadBalancer"
          action = [GET:"updateActivationWorker"]
          constraints {
              operation(inList:["act","dis","stp"])
          }
     }

	  "/dashboard/errors"{
          controller = "dashboard"
          action = "workerErrors"
     }

	  "/dashboard/activation"{
          controller = "dashboard"
          action = "workerActivations"
     }

	  "/dashboard/queue"{
          controller = "dashboard"
          action = "activationQueue"
     }


	   "/"(view:"/help")
	  "500"(view:'/error')
	}
}
