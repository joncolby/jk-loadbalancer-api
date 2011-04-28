

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'apacheFrontend.label', default: 'ApacheFrontend')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${apacheFrontendInstance}">
            <div class="errors">
                <g:renderErrors bean="${apacheFrontendInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${apacheFrontendInstance?.id}" />
                <g:hiddenField name="version" value="${apacheFrontendInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="apacheFrontend.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${apacheFrontendInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="jkstatusURL"><g:message code="apacheFrontend.jkstatusURL.label" default="Jkstatus URL" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'jkstatusURL', 'errors')}">
                                    <g:textField name="jkstatusURL" value="${apacheFrontendInstance?.jkstatusURL}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="user"><g:message code="apacheFrontend.user.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'user', 'errors')}">
                                    <g:textField name="user" value="${apacheFrontendInstance?.user}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password"><g:message code="apacheFrontend.password.label" default="Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'password', 'errors')}">
                                    <g:textField name="password" value="${apacheFrontendInstance?.password}" />
                                </td>
                            </tr>
                        

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="host"><g:message code="apacheFrontend.host.label" default="Host" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'host', 'errors')}">
                                    <g:select name="host.id" from="${Host.list()}" optionKey="id" value="${apacheFrontendInstance?.host?.id}"  />
                                </td>
                            </tr>
                        

                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="loadbalancer"><g:message code="apacheFrontend.loadbalancer.label" default="Loadbalancer" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'loadbalancer', 'errors')}">
                                    
<ul>
<g:each in="${apacheFrontendInstance?.loadbalancer?}" var="l">
    <li><g:link controller="modJkLoadbalancer" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="modJkLoadbalancer" action="create" params="['apacheFrontend.id': apacheFrontendInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'modJkLoadbalancer.label', default: 'ModJkLoadbalancer')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateCreated"><g:message code="apacheFrontend.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${apacheFrontendInstance?.dateCreated}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
