<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ModJkWorker List</title>
    </head>
    <body class="tundra">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ModJkWorker</g:link></span>
        </div>
        <div class="body">
    <g:form method="post" >
        <h1>ModJkWorker Search Criteria</h1>
        <div class="dialog">
            <table>
                <tbody>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="route">Route:</label>
                        </td>
                        <td valign="top" class="value">
                            <input type="text" id="route" name="route" value="${route}"/>
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="host">Host:</label>
                        </td>
                        <td valign="top" class="value">
                            <input type="text" id="host" name="host" value="${host}"/>
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="activation">
                            <label for="activation">Activation:</label>
                        </td>
                        <td valign="top" class="value">
                             <g:select id="activation" name="activation" from="${ModJkWorker.constraints.activation.inList}"
                              value="${activation}" ></g:select>
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="state">
                            <label for="state">State:</label>
                        </td>
                        <td valign="top" class="value">
                             <g:select id="state" name="state" from="${ModJkWorker.constraints.state.inList}"
                              value="${state}" ></g:select>
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="activation">
                            <label for="activation">Changed activation:</label>
                        </td>
                        <td valign="top" class="value">
                             <g:select id="updateActivation" name="updateActivation" from="${ModJkWorker.constraints.activation.inList}"
                              value="${updateActivation}" ></g:select>
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="route">Action:</label>
                        </td>
                        <td valign="top" class="button">
                             <span class="menuButton"><g:actionSubmit class="button" value="searchWorker" /></span>
                        </td>
                        <td valign="top" class="button">
                             <span class="menuButton"><g:actionSubmit class="button" value="updateActivationWorker" /></span>
                        </td>

                    </tr>
                </tbody>
            </table>
        </div>
    </g:form>
            <h1>ModJkWorker List</h1>
             <g:if test="${flash.message}">
             <div class="message">${flash.message}</div>
             </g:if>
              <div class="list">
                <table>
                    <thead>
                        <tr>

                   	         <th>Frontend</th>
                   	         <th>Loadbalancer</th>
                   	        <g:sortableColumn property="name" title="Name" />

                   	        <g:sortableColumn property="host" title="Host" />
                   	        <g:sortableColumn property="address" title="Address" />


                   	        <g:sortableColumn property="state" title="State" />
                        
                   	        <g:sortableColumn property="activation" title="Activation" />
                        


                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${modJkWorkerList}" status="i" var="modJkWorker">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${modJkWorker.loadbalancer.frontend.name}</td>
                            <td>${fieldValue(bean:modJkWorker, field:'loadbalancer')}</td>
                            <td><g:link action="show" id="${modJkWorker.id}">${fieldValue(bean:modJkWorker, field:'name')}</g:link></td>
                        
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
