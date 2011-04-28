

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show TomcatInstance</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">TomcatInstance List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New TomcatInstance</g:link></span>
        </div>
        <div class="body">
            <h1>Show TomcatInstance</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tomcatInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tomcatInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Absolute Path:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tomcatInstance, field:'absolutePath')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Host:</td>
                            
                            <td valign="top" class="value"><g:link controller="host" action="show" id="${tomcatInstance?.host?.id}">${tomcatInstance?.host?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Port Prefix:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tomcatInstance, field:'portPrefix')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Release:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tomcatInstance, field:'release')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Status:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tomcatInstance, field:'status')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Webartifacts:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="w" in="${tomcatInstance.webartifacts}">
                                    <li><g:link controller="webartifact" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Workers:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="w" in="${tomcatInstance.workers}">
                                    <li><g:link controller="modJkWorker" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${tomcatInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
