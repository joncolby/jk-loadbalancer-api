
import org.hibernate.FetchMode as FM

class ModJkWorkerController {

    def JkService jkService

    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def updateActivationWorker = {
        def  workers
        if (params.updateActivation) {
            workers = jkService.searchWorkers(params.route, params.host, params.activation, params.state)
            workers.each { ModJkWorker worker ->
                jkService.updateActivation(worker, params.updateActivation)
                worker.activation = params.updateActivation
                worker.save()
                log.debug worker
            }
        }
        render(view: 'list', model: [modJkWorkerList: workers,
                route: params.route,
                host: params.host,
                activation: params.activation,
                state: params.state,
                updateActivation: params.updateActivation])
    }

    def searchWorker = {

      /**  def workers = null
        if(params?.route ) {
            def i = params.route.indexOf(',')
            if(i == -1) {
                def c = ModJkWorker.createCriteria()
                 workers = c.list() {
                     if(params.state != 'NONE' || params.activation != 'NONE') {
                         and {
                             if(params.activation && params.activation != 'NONE')
                                 eq('activation', params.activation)
                             if(params.state && params.state != 'NONE')
                                 eq('state', params.state)
                             like('route',"${params.route}%")
                             if(params.host) {
                                loadbalancer {
                                     frontend {
                                         like('name',"%${params.host}%")
                                    }
                                }
                             }
                         }
                     } else {
                         and {
                            like('route',"${params.route}%")
                            if(params.host) {
                                like('host',"%${params.host}%")                                  
                            }
                         }
                     }
                     fetchMode('loadbalancer', FM.EAGER)
                     order('name', 'asc')
                 }
            } else {
                def c = ModJkWorker.createCriteria()
                if(params.activation && params.activation != 'NONE') {
                    workers = c.list() {
                      and {
                         eq('activation',params.activation)
                         if(params.state && params.state != 'NONE')
                            eq('state', params.state)
                         or {
                             for(line in params.route.split(",")) {
                                 like('route',"${line}%")
                             }
                         }
                      }
                     fetchMode('loadbalancer', FM.EAGER)
                     order('name', 'asc')
                     }
                } else {
                    workers = c.list() {
                        if(params.state && params.state != 'NONE') {
                            and {
                                eq('state', params.state)
                                or {
                                    for(line in params.route.split(",")) {
                                        like('route',"${line}%")
                                    }
                                }
                            }
                        } else {
                            or {
                                for(line in params.route.split(",")) {
                                    like('route',"${line}%")
                                }
                            }
                        }
                        fetchMode('loadbalancer', FM.EAGER)
                        order('name', 'asc')
                    }
                 }
            }
        } else {
            if(params.host) {
                def c = ModJkWorker.createCriteria()
                workers = c.list() {
                    if(params.state != 'NONE' || params.activation != 'NONE') {
                        and {
                            if(params.activation && params.activation != 'NONE')
                                eq('activation', params.activation)
                            if(params.state && params.state != 'NONE')
                                eq('state', params.state)
                            loadbalancer {
                                frontend {
                                    like('name',"%${params.host}%")
                                }
                            }
                        }
                    } else {
                        loadbalancer {
                            frontend {
                                like('name',"%${params.host}%")
                            }
                        }
                    }
                    fetchMode('loadbalancer', FM.EAGER)
                    order('name', 'asc')
                }

            } else {
                def c = ModJkWorker.createCriteria()
                 workers = c.list() {
                     if(params.state != 'NONE' || params.activation != 'NONE') {
                         and {
                             if(params.activation && params.activation != 'NONE')
                                 eq('activation', params.activation)
                             if(params.state && params.state != 'NONE')
                                 eq('state', params.state)
                         }
                     }
                     fetchMode('loadbalancer', FM.EAGER)
                     order('name', 'asc')
                  }
            }
        }
       */
       
       
       def workers =  jkService.searchWorkers(params.route,params.host,params.activation,params.state)
        render(view:'list', model: [modJkWorkerList:workers,
                route:params.route,
                host:params.host,
                activation:params.activation,
                state:params.state])
    }
    
    def tree = {
    }

    def list = {
        //if(!params.max) params.max = 10
        [ modJkWorkerList: ModJkWorker.list( params ) ]
    }

    

    def show = {
    		
    	//def modJkWorker =  ModJkWorker.findByHost(params.id)
    		
        def modJkWorker = ModJkWorker.get( params.id )

        if(!modJkWorker) {
            flash.message = "ModJkWorker not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ modJkWorker : modJkWorker ] }
    }



    def delete = {
        def modJkWorker = ModJkWorker.get( params.id )
        if(modJkWorker) {
            modJkWorker.delete()
            flash.message = "ModJkWorker ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ModJkWorker not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def modJkWorker = ModJkWorker.get( params.id )

        if(!modJkWorker) {
            flash.message = "ModJkWorker not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ modJkWorker : modJkWorker ]
        }
    }

    def update = {
        ModJkWorker modJkWorker = ModJkWorker.get( params.id )
        if(modJkWorker) {
            def activation = params.activation
            if(!activation.equals(modJkWorker.activation)) {
                jkService.updateActivation(modJkWorker, activation)
            }
            // Only route and activation allowed
            // if Route change instance also Change!!
            // check jvmROute at instance and port/address
            modJkWorker.properties = params
            if(!modJkWorker.hasErrors() && modJkWorker.save()) {
                flash.message = "ModJkWorker ${params.id} updated"
                redirect(action:show,id:modJkWorker.id)
            }
            else {
                render(view:'edit',model:[modJkWorker:modJkWorker])
            }
        }
        else {
            flash.message = "ModJkWorker not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def modJkWorker = new ModJkWorker()
        modJkWorker.properties = params
        return ['modJkWorker':modJkWorker]
    }

    def save = {
        def modJkWorker = new ModJkWorker(params)
        
        if(!modJkWorker.hasErrors() && modJkWorker.save()) {
            flash.message = "ModJkWorker ${modJkWorker.id} created"
            redirect(action:show,id:modJkWorker.id)
        }
        else {
            render(view:'create',model:[modJkWorker:modJkWorker])
        }
    }
}
