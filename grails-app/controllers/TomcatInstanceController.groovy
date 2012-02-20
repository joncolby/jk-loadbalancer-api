/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class TomcatInstanceController {
    
    def index = { redirect(action:list,params:params) }

    def MasterTomcatService masterTomcatService

    // the delete, save and update actions only accept POST requests
    static def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def status = {
       def instances = TomcatInstance.list(params) 
       [instances: instances]
    }
    
    def startRemote = {
        def tomcatInstance = TomcatInstance.get(params.id)
        if (tomcatInstance) {
            masterTomcatService.startRemoteTomcat(tomcatInstance)
            tomcatInstance.status = "running"
            tomcatInstance.save()
            flash.message = "TomcatInstance ${tomcatInstance.name} started"
            render(view: 'show', model: [tomcatInstance: tomcatInstance])
        } else {
            flash.message = "TomcatInstance not found with id ${params.id}"
            redirect(action: list)
        }
    }

    def stopRemote = {
        def tomcatInstance = TomcatInstance.get(params.id)
        if (tomcatInstance) {
            masterTomcatService.stopRemoteTomcat(tomcatInstance)
            tomcatInstance.status = "stopped"
            tomcatInstance.save()
            flash.message = "TomcatInstance ${tomcatInstance.name} stopped"
            render(view: 'show', model: [tomcatInstance: tomcatInstance])
        } else {
            flash.message = "TomcatInstance not found with id ${params.id}"
            redirect(action: list)
        }
    }

    def infoJkWorker = {
        def lb

        if(params.id) {
            def tomcatInstance = TomcatInstance.get( params.id )
            def matcher = tomcatInstance.name =~ /(\w+)(\d{2})_((i|c)\d{2})_(\d{3,4})/
            if(matcher.count == 1) {
                lb = matcher[0][1] + 'lb'
                log.debug "search for $lb"
            }
        } else
            if(params.lb)
                lb = params.lb
        if(lb) {
            def loadbalancers = ModJkLoadbalancer.findAllByName(lb)
            // FIXME loadbalancer not found!
            def ArrayList workers = new ArrayList();
            loadbalancers.each {
                loadbalancer ->
                def infoWorker = new InfoJkWorker()
                infoWorker.name = loadbalancer.name
                infoWorker.hostname = loadbalancer.frontend.name
                //infoWorker.count = loadbalancer.workers.count
                loadbalancer.workers.each { ModJkWorker worker ->
                    switch(worker.activation) {
                        case 'STP':
                            infoWorker.stopped.add(worker.instance.name)
                            break
                        case 'DIS' :
                            infoWorker.disabled.add(worker.instance.name)
                            break
                        default :
                            log.debug "$worker.name active"
                            break
                    }
                    switch(worker.state) {
                        case 'OK/IDLE':
                            infoWorker.idle++
                            break
                        case 'OK' :
                            infoWorker.ok++
                            break
                        default : infoWorker.error++
                    }
                }
                workers.add(infoWorker)
            }
            render(view:'infoJkWorker', model:[workerList:workers])
        } else {
            flash.message = "Loadbalancer to show jkstatus worker stats not found!"
            redirect(controller:ModJkLoadbalancer, action:list)
        }

    }

    def list = {
        // if(!params.max) params.max = 10
        [ tomcatInstanceList: TomcatInstance.list( params ) ]
    }

    def show = {
        def tomcatInstance = TomcatInstance.get( params.id )

        if(!tomcatInstance) {
            flash.message = "TomcatInstance not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ tomcatInstance : tomcatInstance ] }
    }

    def delete = {
        def tomcatInstance = TomcatInstance.get( params.id )
        if(tomcatInstance) {
            tomcatInstance.delete()
            flash.message = "TomcatInstance ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "TomcatInstance not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def tomcatInstance = TomcatInstance.get( params.id )

        if(!tomcatInstance) {
            flash.message = "TomcatInstance not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ tomcatInstance : tomcatInstance ]
        }
    }

    def update = {
        def tomcatInstance = TomcatInstance.get( params.id )
        if(tomcatInstance) {
            tomcatInstance.properties = params
            if(!tomcatInstance.hasErrors() && tomcatInstance.save()) {
                flash.message = "TomcatInstance ${params.id} updated"
                redirect(action:show,id:tomcatInstance.id)
            }
            else {
                render(view:'edit',model:[tomcatInstance:tomcatInstance])
            }
        }
        else {
            flash.message = "TomcatInstance not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def tomcatInstance = new TomcatInstance()
        tomcatInstance.properties = params
        return ['tomcatInstance':tomcatInstance]
    }

    def save = {
        def tomcatInstance = new TomcatInstance(params)
        if(!tomcatInstance.hasErrors() && tomcatInstance.save()) {
            flash.message = "TomcatInstance ${tomcatInstance.id} created"
            redirect(action:show,id:tomcatInstance.id)
        }
        else {
            render(view:'create',model:[tomcatInstance:tomcatInstance])
        }
    }
}
