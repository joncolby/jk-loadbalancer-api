

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'apacheFrontend.label', default: 'ApacheFrontend')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'apacheFrontend.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'apacheFrontend.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="jkstatusURL" title="${message(code: 'apacheFrontend.jkstatusURL.label', default: 'Jkstatus URL')}" />
                        
                            <g:sortableColumn property="user" title="${message(code: 'apacheFrontend.user.label', default: 'User')}" />
                        
                            <g:sortableColumn property="password" title="${message(code: 'apacheFrontend.password.label', default: 'Password')}" />
                        
                            <g:sortableColumn property="jkversion" title="${message(code: 'apacheFrontend.jkversion.label', default: 'Jkversion')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${apacheFrontendInstanceList}" status="i" var="apacheFrontendInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${apacheFrontendInstance.id}">${fieldValue(bean: apacheFrontendInstance, field: "id")}</g:link></td>
                        
                            <td><g:link action="show" id="${apacheFrontendInstance.id}">${fieldValue(bean: apacheFrontendInstance, field: "name")}</g:link></td>
                        
                            <td><g:link action="show" id="${apacheFrontendInstance.id}">${fieldValue(bean: apacheFrontendInstance, field: "jkstatusURL")}</g:link></td>
                        
                            <td><g:link action="show" id="${apacheFrontendInstance.id}">${fieldValue(bean: apacheFrontendInstance, field: "user")}</g:link></td>
                        
                            <td><g:link action="show" id="${apacheFrontendInstance.id}">${fieldValue(bean: apacheFrontendInstance, field: "password")}</g:link></td>
                        
                            <td><g:link action="show" id="${apacheFrontendInstance.id}">${fieldValue(bean: apacheFrontendInstance, field: "jkversion")}</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${apacheFrontendInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
