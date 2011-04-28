
<%@ page import="Audit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="large" />
        <g:set var="entityName" value="${message(code: 'dashboard.error.label', default: 'Errors')}" />
        <title>Tomcat Workers Activation Queue</title>
    </head>
    <body>

        <div class="body">
            <h1>Tomcat Workers Activation Queue (${total} total)</h1>
            <h3>Current Time: <g:formatDate date="${new Date()}" format="yyyy-MM-dd HH:mm:ss"  /></h3>
            </BR>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                       
                            <th>Worker Name</th>
                   
                            <g:sortableColumn property="newActivation" title="New Activation" />
                        
                            <g:sortableColumn property="newActivationTime" title="New Activation Time" />

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${items}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

    						<td><jklb:getWorker id="${item.workerId}" /></td>
                            
                            <td>${fieldValue(bean: item, field: "newActivation")}</td>
                            
                           <td><g:formatDate date="${item.newActivationTime}" format="yyyy-MM-dd HH:mm:ss"  /></td>


  
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${total}" />
            </div>
        </div>
    </body>
</html>
