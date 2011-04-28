

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'apacheFrontend.label', default: 'ApacheFrontend')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apacheFrontendInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apacheFrontendInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.jkstatusURL.label" default="Jkstatus URL" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apacheFrontendInstance, field: "jkstatusURL")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.user.label" default="User" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apacheFrontendInstance, field: "user")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.password.label" default="Password" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apacheFrontendInstance, field: "password")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.jkversion.label" default="Jkversion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apacheFrontendInstance, field: "jkversion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.host.label" default="Host" /></td>
                            
                            <td valign="top" class="value"><g:link controller="host" action="show" id="${apacheFrontendInstance?.host?.id}">${apacheFrontendInstance?.host?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${apacheFrontendInstance?.lastUpdated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.loadbalancer.label" default="Loadbalancer" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${apacheFrontendInstance.loadbalancer}" var="l">
                                    <li><g:link controller="modJkLoadbalancer" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apacheFrontend.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${apacheFrontendInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${apacheFrontendInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
