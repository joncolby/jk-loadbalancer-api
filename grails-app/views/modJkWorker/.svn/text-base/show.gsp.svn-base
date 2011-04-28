

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ModJkWorker</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ModJkWorker List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ModJkWorker</g:link></span>
        </div>
        <div class="body">
            <h1>Show ModJkWorker</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Port:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'port')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">State:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'state')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Activation:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'activation')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Instance:</td>
                            
                            <td valign="top" class="value"><g:link controller="tomcatInstance" action="show" id="${modJkWorker?.instance?.id}">${modJkWorker?.instance?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'address')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Host:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'host')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Loadbalancer:</td>
                            
                            <td valign="top" class="value"><g:link controller="modJkLoadbalancer" action="show" id="${modJkWorker?.loadbalancer?.id}">${modJkWorker?.loadbalancer?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Route:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'route')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${modJkWorker?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
                <span class="button"><g:link controller="modJkWorkerStatistic" action="listByWorker" id="${modJkWorker?.id}">List all Stats</g:link></span>
                <span class="button"><g:link controller="modJkWorkerStatistic" action="showLineImage" id="${modJkWorker?.id}">Show Stats Image</g:link></span>
            </div>
        </div>
    </body>
</html>
