

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ModJkWorkerStatistic</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ModJkWorkerStatistic List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ModJkWorkerStatistic</g:link></span>
        </div>
        <div class="body">
            <h1>Show ModJkWorkerStatistic</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorkerStatistic, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Access Counter:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorkerStatistic, field:'accessCounter')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Busy Connections:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorkerStatistic, field:'busyConnections')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Client Errors:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorkerStatistic, field:'clientErrors')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Error Counter:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorkerStatistic, field:'errorCounter')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Read Bytes:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorkerStatistic, field:'readBytes')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Worker:</td>
                            
                            <td valign="top" class="value"><g:link controller="modJkWorker" action="show" id="${modJkWorkerStatistic?.worker?.id}">${modJkWorkerStatistic?.worker?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Write Bytes:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorkerStatistic, field:'writeBytes')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${modJkWorkerStatistic?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
