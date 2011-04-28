

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ModJkWorker</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ModJkWorker List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ModJkWorker</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ModJkWorker</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${modJkWorker}">
            <div class="errors">
                <g:renderErrors bean="${modJkWorker}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${modJkWorker?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'name')}</td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'address')}</td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state">State:</label>
                                </td>
                                <td valign="top" class="value">${fieldValue(bean:modJkWorker, field:'state')}</td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="instance">Instance:</label>
                                </td>
                                <td valign="top" class="value"><g:link controller="tomcatInstance" action="show" id="${modJkWorker?.instance?.id}">${modJkWorker?.instance?.encodeAsHTML()}</g:link></td>

                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="loadbalancer">Loadbalancer:</label>
                                </td>
                                <td valign="top" class="value"><g:link controller="modJkLoadbalancer" action="show" id="${modJkWorker?.loadbalancer?.id}">${modJkWorker?.loadbalancer?.encodeAsHTML()}</g:link></td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activation">Activation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorker,field:'activation','errors')}">
                                    <g:select id="activation" name="activation" from="${modJkWorker.constraints.activation.inList}" value="${modJkWorker.activation}" ></g:select>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="route">Route:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorker,field:'route','errors')}">
                                    <input type="text" id="route" name="route" value="${fieldValue(bean:modJkWorker,field:'route')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
