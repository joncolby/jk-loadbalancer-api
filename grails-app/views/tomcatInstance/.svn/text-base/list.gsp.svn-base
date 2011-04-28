

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>TomcatInstance List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New TomcatInstance</g:link></span>
        </div>
        <div class="body">
            <h1>TomcatInstance List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="absolutePath" title="Absolute Path" />
                        
                   	        <th>Host</th>
                   	    
                   	        <g:sortableColumn property="portPrefix" title="Port Prefix" />
                        
                   	        <g:sortableColumn property="release" title="Release" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tomcatInstanceList}" status="i" var="tomcatInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tomcatInstance.id}">${fieldValue(bean:tomcatInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:tomcatInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:tomcatInstance, field:'absolutePath')}</td>
                        
                            <td>${fieldValue(bean:tomcatInstance, field:'host')}</td>
                        
                            <td>${fieldValue(bean:tomcatInstance, field:'portPrefix')}</td>
                        
                            <td>${fieldValue(bean:tomcatInstance, field:'release')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${TomcatInstance.count()}" />
            </div>
        </div>
    </body>
</html>
