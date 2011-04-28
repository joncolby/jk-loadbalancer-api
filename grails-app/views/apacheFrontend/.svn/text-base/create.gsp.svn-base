

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'apacheFrontend.label', default: 'ApacheFrontend')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${apacheFrontendInstance}">
            <div class="errors">
                <g:renderErrors bean="${apacheFrontendInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="apacheFrontend.name.label" default="Fully Qualified Hostname" /> (example: front45-7.mobile.rz)</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${apacheFrontendInstance?.name}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user"><g:message code="apacheFrontend.user.label" default="User" /> (leave blank if not)</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'user', 'errors')}">
                                    <g:textField name="user" value="${apacheFrontendInstance?.user}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="password"><g:message code="apacheFrontend.password.label" default="Password" /> (leave blank if none)</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apacheFrontendInstance, field: 'password', 'errors')}">
                                    <g:textField name="password" value="${apacheFrontendInstance?.password}" />
                                </td>
                            </tr>
                        

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
