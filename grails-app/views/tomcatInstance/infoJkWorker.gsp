<html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
     <meta name="layout" content="main" />
     <title>Info Worker List</title>
 </head>
 <body>
     <div class="nav">
         <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
     </div>
     <div class="body">
         <g:if test="${flash.message}">
         <div class="message">${flash.message}</div>
         </g:if>
        <div class="list">
                <table>
                    <thead>
                        <tr>
                   	        <th>Host</th>
                   	        <th>Loadbalancer</th>
                   	        <th>OK</th>
                   	        <th>OK/IDLE</th>
                   	        <th>ERR*</th>
                   	        <th>Stopped</th>
                   	        <th>Disabled</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${workerList}" status="i" var="worker">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${worker.hostname}</td>
                            <td>${worker.name}</td>
                            <td>${worker.ok}</td>
                            <td>${worker.idle}</td>
                            <td>${worker.error}</td>
                            <td>${worker.stopped}</td>
                            <td>${worker.disabled}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
     </div>
</body>
</html>