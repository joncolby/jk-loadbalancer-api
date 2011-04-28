
<%@ page import="Audit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="large" />
        <g:set var="entityName" value="${message(code: 'dashboard.error.label', default: 'Errors')}" />
        <title>${entityName}</title>
    </head>
    <body>

        <div class="body">
            <h1>Tomcat Workers in Error Status (${notOKTotal} total)</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                       
                            <g:sortableColumn property="name" title="Worker Name" />
                        
                            <g:sortableColumn property="host" title="Host" />
                        
                            <g:sortableColumn property="state" title="State" />
                        
                            <g:sortableColumn property="activation" title="Activation" />

                            <g:sortableColumn property="loadbalancer" title="Front" />
                            
                             <g:sortableColumn property="lastUpdated" title="Last Updated" />

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${notOKList}" status="i" var="notOK">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                            <td>${fieldValue(bean: notOK, field: "name")}</td>
                            
                            <td>${fieldValue(bean: notOK, field: "host")}</td>
                            
                            <td>${fieldValue(bean: notOK, field: "state")}</td>
                            
                            <td>${fieldValue(bean: notOK, field: "activation")}</td>

							<td><a href="${fieldValue(bean: notOK, field: "loadbalancer.frontend.jkstatusURL")}?cmd=show&w=${notOK.loadbalancer.name}">${notOK.loadbalancer.frontend}</a></td>

                          <td><g:formatDate date="${notOK.lastUpdated}" format="yyyy-MM-dd HH:mm:ss"  /> </td>
  
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${notOKTotal}" />
            </div>
        </div>
    </body>
</html>
