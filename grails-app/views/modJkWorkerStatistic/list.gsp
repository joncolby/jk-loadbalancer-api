

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ModJkWorkerStatistic List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ModJkWorkerStatistic</g:link></span>
        </div>
        <div class="body">
            <h1>ModJkWorkerStatistic List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="dateCreated" title="Date" />
                   	        <g:sortableColumn property="accessCounter" title="Access Counter" />
                        
                   	        <g:sortableColumn property="busyConnections" title="Busy Connections" />
                        
                   	        <g:sortableColumn property="clientErrors" title="Client Errors" />
                        
                   	        <g:sortableColumn property="errorCounter" title="Error Counter" />
                        
                   	        <g:sortableColumn property="readBytes" title="Read Bytes" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${modJkWorkerStatisticList}" status="i" var="modJkWorkerStatistic">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${modJkWorkerStatistic.id}">${fieldValue(bean:modJkWorkerStatistic, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:modJkWorkerStatistic, field:'dateCreated')}</td>
                            <td>${fieldValue(bean:modJkWorkerStatistic, field:'accessCounter')}</td>
                        
                            <td>${fieldValue(bean:modJkWorkerStatistic, field:'busyConnections')}</td>
                        
                            <td>${fieldValue(bean:modJkWorkerStatistic, field:'clientErrors')}</td>
                        
                            <td>${fieldValue(bean:modJkWorkerStatistic, field:'errorCounter')}</td>
                        
                            <td>${fieldValue(bean:modJkWorkerStatistic, field:'readBytes')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ModJkWorkerStatistic.count()}" />
            </div>
        </div>
    </body>
</html>
