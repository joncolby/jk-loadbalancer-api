

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ModJkWorker</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ModJkWorker List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ModJkWorker</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${modJkWorker}">
            <div class="errors">
                <g:renderErrors bean="${modJkWorker}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="instance">Instance:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorker,field:'instance','errors')}">
                                    <g:select optionKey="id" from="${TomcatInstance.list()}" name="instance.id" value="${modJkWorker?.instance?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="loadbalancer">Loadbalancer:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorker,field:'loadbalancer','errors')}">
                                    <g:select optionKey="id" from="${ModJkLoadbalancer.list()}" name="loadbalancer.id" value="${modJkWorker?.loadbalancer?.id}" ></g:select>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorker,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:modJkWorker,field:'name')}"/>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activation">Activation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:modJkWorker,field:'activation','errors')}">
                                    <g:select id="activation" name="activation" from="${modJkWorker.constraints.activation.inList}" value="${modJkWorker.activation}" ></g:select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
