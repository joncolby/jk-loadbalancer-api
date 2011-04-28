
<%@ page import="Audit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="large" />
        <g:set var="entityName" value="${message(code: 'dashboard.activation.label', default: 'Dashboard')}" />
        <title>${entityName}</title>
    </head>
    <body>

        <div class="body">
            <h1>Tomcat Workers with Non-Active Status (${notActivesTotal} total)</h1>
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
                             
                             <g:sortableColumn property="id" title="id" />

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${notActivesList}" status="i" var="notActive">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                            <td>${fieldValue(bean: notActive, field: "name")}</td>
                            
                            <td>${fieldValue(bean: notActive, field: "host")}</td>
                            
                            <td>${fieldValue(bean: notActive, field: "state")}</td>
                            
                            <td>${fieldValue(bean: notActive, field: "activation")}</td>
                            
                           <td><a href="${fieldValue(bean: notActive, field: "loadbalancer.frontend.jkstatusURL")}?cmd=show&w=${notActive.loadbalancer.name}">${notActive.loadbalancer.frontend}</a></td>
                        
                          <td><g:formatDate date="${notActive.lastUpdated}" format="yyyy-MM-dd HH:mm:ss"  /></td>
  
						   <td>${notActive.id}</td>
						   
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${notActivesTotal}" />
            </div>
        </div>
    </body>
</html>
