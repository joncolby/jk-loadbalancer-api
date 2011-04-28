<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    </head>
    <body class="tundra">
        <div class="body">

            <h1>ModJkWorker List for ${host}</h1>
             <g:if test="${flash.message}">
             <div class="message">${flash.message}</div>
             </g:if>
              <div class="list">
                <table>
                    <thead>
                        <tr>
                   	         <th>Frontend</th>
                   	         <th>Loadbalancer</th>
                   	         <th>Name</th>
					         <th>Host</th>
					         <th>Address</th>
					         <th>State</th>
					         <th>Activation</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${modJkWorkerList}" status="i" var="modJkWorker">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${modJkWorker.loadbalancer.frontend.name}</td>
                            <td>${fieldValue(bean:modJkWorker, field:'loadbalancer')}</td>
                            <td>${fieldValue(bean:modJkWorker, field:'name')}</td>
                        
                            <td>${fieldValue(bean:modJkWorker, field:'host')}</td>
                           <td>${fieldValue(bean:modJkWorker, field:'address')}</td>


                            <td>${fieldValue(bean:modJkWorker, field:'state')}</td>
                        
                            <td>${fieldValue(bean:modJkWorker, field:'activation')}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
