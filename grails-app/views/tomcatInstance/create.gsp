

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create TomcatInstance</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">TomcatInstance List</g:link></span>
        </div>
        <div class="body">
            <h1>Create TomcatInstance</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tomcatInstance}">
            <div class="errors">
                <g:renderErrors bean="${tomcatInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tomcatInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:tomcatInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="absolutePath">Absolute Path:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tomcatInstance,field:'absolutePath','errors')}">
                                    <input type="text" id="absolutePath" name="absolutePath" value="${fieldValue(bean:tomcatInstance,field:'absolutePath')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="host">Host:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tomcatInstance,field:'host','errors')}">
                                    <g:select optionKey="id" from="${Host.list()}" name="host.id" value="${tomcatInstance?.host?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portPrefix">Port Prefix:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tomcatInstance,field:'portPrefix','errors')}">
                                    <input type="text" id="portPrefix" name="portPrefix" value="${fieldValue(bean:tomcatInstance,field:'portPrefix')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="release">Release:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tomcatInstance,field:'release','errors')}">
                                    <input type="text" id="release" name="release" value="${fieldValue(bean:tomcatInstance,field:'release')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status">Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tomcatInstance,field:'status','errors')}">
                                    <input type="text" id="status" name="status" value="${fieldValue(bean:tomcatInstance,field:'status')}"/>
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
