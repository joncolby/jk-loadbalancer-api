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

import javax.imageio.ImageIO
import org.jfree.chart.ChartFactory
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.chart.plot.PlotOrientation as Orientation
import java.text.SimpleDateFormat;

class ModJkWorkerStatisticController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def listByWorker = {
        if(!params.max) params.max = 10
        if(params.id) {
            def worker = ModJkWorker.get(params.id)
            render(view:'list',model:[modJkWorkerStatisticList: ModJkWorkerStatistic.findAllByWorker( worker,params ) ])
        } else
            render(view:'list',model:[modJkWorkerStatisticList: null])


    }

    def list = {
        if(!params.max) params.max = 10
        [ modJkWorkerStatisticList: ModJkWorkerStatistic.list( params ) ]
    }

    def show = {
        def modJkWorkerStatistic = ModJkWorkerStatistic.get( params.id )

        if(!modJkWorkerStatistic) {
            flash.message = "ModJkWorkerStatistic not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ modJkWorkerStatistic : modJkWorkerStatistic ] }
    }

    def delete = {
        def modJkWorkerStatistic = ModJkWorkerStatistic.get( params.id )
        if(modJkWorkerStatistic) {
            modJkWorkerStatistic.delete()
            flash.message = "ModJkWorkerStatistic ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "ModJkWorkerStatistic not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def modJkWorkerStatistic = ModJkWorkerStatistic.get( params.id )

        if(!modJkWorkerStatistic) {
            flash.message = "ModJkWorkerStatistic not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ modJkWorkerStatistic : modJkWorkerStatistic ]
        }
    }

    def update = {
        def modJkWorkerStatistic = ModJkWorkerStatistic.get( params.id )
        if(modJkWorkerStatistic) {
            modJkWorkerStatistic.properties = params
            if(!modJkWorkerStatistic.hasErrors() && modJkWorkerStatistic.save()) {
                flash.message = "ModJkWorkerStatistic ${params.id} updated"
                redirect(action:show,id:modJkWorkerStatistic.id)
            }
            else {
                render(view:'edit',model:[modJkWorkerStatistic:modJkWorkerStatistic])
            }
        }
        else {
            flash.message = "ModJkWorkerStatistic not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def modJkWorkerStatistic = new ModJkWorkerStatistic()
        modJkWorkerStatistic.properties = params
        return ['modJkWorkerStatistic':modJkWorkerStatistic]
    }

    def save = {
        def modJkWorkerStatistic = new ModJkWorkerStatistic(params)
        if(!modJkWorkerStatistic.hasErrors() && modJkWorkerStatistic.save()) {
            flash.message = "ModJkWorkerStatistic ${modJkWorkerStatistic.id} created"
            redirect(action:show,id:modJkWorkerStatistic.id)
        }
        else {
            render(view:'create',model:[modJkWorkerStatistic:modJkWorkerStatistic])
        }
    }

    def showLineImage = {
        if(params.id) {
            def worker = ModJkWorker.get(params.id)
            def time = System.currentTimeMillis() - 300 *1000
            // FIXME only get stats from last 5 Minutes / sort createDate
            def stats = ModJkWorkerStatistic.findAllByWorker(worker)
            def dataset = new DefaultCategoryDataset()
            def formatter = new SimpleDateFormat("HH:mm");
            def accessCounter = 0
            def i = 0
            stats.each { stat ->
                def java.sql.Timestamp stattime = stat.dateCreated
                if(stattime.getTime() > time) {
                    def date = formatter.format(new Date(stattime.getTime()))
                    if(i > 0)
                        accessCounter = stats[i-1].accessCounter
                    else
                        accessCounter = stats[i].accessCounter
                    dataset.addValue stat.accessCounter - accessCounter, "accessCounter", "$date"
                    dataset.addValue stat.errorCounter, "errorCounter", "$date"
                    dataset.addValue stat.clientErrors, "clientErrors", "$date"
                    dataset.addValue stat.busyConnections, "busyConnections", "$date"

                }
                i++
            }
            def dateformat = new SimpleDateFormat("yyyy-MM-dd z" ).format(new Date(time))
            def labels = ["Worker Stats $worker.loadbalancer.frontend.name $worker.loadbalancer.name ${worker.name}", "${dateformat}", "Counter"]
            def options = [true, true, true]
            def chart = ChartFactory.createLineChart (* labels, dataset,
            Orientation.VERTICAL, * options)
            def image = chart.createBufferedImage (400, 400)

            response.setContentType ("image/png")
            ImageIO.write(image, "png", response.outputStream)
        }
    }
}
