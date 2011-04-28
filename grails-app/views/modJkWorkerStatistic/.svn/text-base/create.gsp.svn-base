

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ModJkWorkerStatistic</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ModJkWorkerStatistic List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ModJkWorkerStatistic</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${modJkWorkerStatistic}">
            <div class="errors">
                <g:renderErrors bean="${modJkWorkerStatistic}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accessCounter">Access Counter:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorkerStatistic,field:'accessCounter','errors')}">
                                    <input type="text" id="accessCounter" name="accessCounter" value="${fieldValue(bean:modJkWorkerStatistic,field:'accessCounter')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="busyConnections">Busy Connections:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorkerStatistic,field:'busyConnections','errors')}">
                                    <input type="text" id="busyConnections" name="busyConnections" value="${fieldValue(bean:modJkWorkerStatistic,field:'busyConnections')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="clientErrors">Client Errors:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorkerStatistic,field:'clientErrors','errors')}">
                                    <input type="text" id="clientErrors" name="clientErrors" value="${fieldValue(bean:modJkWorkerStatistic,field:'clientErrors')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="errorCounter">Error Counter:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorkerStatistic,field:'errorCounter','errors')}">
                                    <input type="text" id="errorCounter" name="errorCounter" value="${fieldValue(bean:modJkWorkerStatistic,field:'errorCounter')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="readBytes">Read Bytes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorkerStatistic,field:'readBytes','errors')}">
                                    <input type="text" id="readBytes" name="readBytes" value="${fieldValue(bean:modJkWorkerStatistic,field:'readBytes')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="worker">Worker:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorkerStatistic,field:'worker','errors')}">
                                    <g:select optionKey="id" from="${ModJkWorker.list()}" name="worker.id" value="${modJkWorkerStatistic?.worker?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="writeBytes">Write Bytes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorkerStatistic,field:'writeBytes','errors')}">
                                    <input type="text" id="writeBytes" name="writeBytes" value="${fieldValue(bean:modJkWorkerStatistic,field:'writeBytes')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
