
<%@ page import="Audit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'audit.label', default: 'Audit')}" />
        <title><g:message code="audit.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
        </div>
        <div class="body">
            <h1><g:message code="audit.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                        	<%--
                            <g:sortableColumn property="id" title="${message(code: 'audit.id.label', default: 'Id')}" />
                            --%>
                        
                            <g:sortableColumn property="ipAddress" title="${message(code: 'audit.ipAddress.label', default: 'Ip Address')}" />
                        
                            <g:sortableColumn property="target" title="${message(code: 'audit.target.label', default: 'Target')}" />
                        
                            <g:sortableColumn property="command" title="${message(code: 'audit.command.label', default: 'Command')}" />
                        
                            <g:sortableColumn property="userAgent" title="${message(code: 'audit.userAgent.label', default: 'User Agent')}" />
                            
                              <g:sortableColumn property="result" title="${message(code: 'audit.userAgent.label', default: 'Result')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'audit.dateCreated.label', default: 'Date')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${auditInstanceList}" status="i" var="auditInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <%-- 
                            <td>${fieldValue(bean: auditInstance, field: "id")}</td> 
                            --%>
                        
                            <td>${fieldValue(bean: auditInstance, field: "ipAddress")}</td>
                        
                            <td>${fieldValue(bean: auditInstance, field: "target")}</td>
                        
                            <td>${fieldValue(bean: auditInstance, field: "command")}</td>
                        
                            <td>${fieldValue(bean: auditInstance, field: "userAgent")}</td>
                            
                            <td>${fieldValue(bean: auditInstance, field: "result")}</td>
                        
                            <td><g:formatDate date="${auditInstance.dateCreated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${auditInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
