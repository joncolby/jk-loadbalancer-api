
<%@ page import="Audit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="large" />
        <g:set var="entityName" value="${message(code: 'audit.label', default: 'Audit')}" />
        <title>jklb dashboard</title>
    </head>
    <body>
<p><a href="<g:createLink controller="dashboard" action="errors" />">Error Dashboard</a> </p>
<p><a href="<g:createLink controller="dashboard" action="activation" />">Activation Dashboard</a></p>
<p><a href="<g:createLink controller="dashboard" action="queue" />">Activation Queue</a></p>
    </body>
</html>
