<html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
     <meta name="layout" content="main" />
     <title>Instance List</title>
 </head>
 <body>
     <div class="nav">
         <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
     </div>
     <div class="body">
         <g:if test="${flash.message}">
         <div class="message">${flash.message}</div>
         </g:if>
        <ul>
        <g:each in="${instances}" var="instance">
            <li><g:link controller="tomcatInstance" action="show" id="${instance.id}">${fieldValue(bean:instance, field:'name')} (${instance.release})</g:link>
             <i><g:if test="${instance.status == 'stopped'}">
                <g:link controller="tomcatInstance" action="startRemote" id="${instance.id}">Start</g:link>
             </g:if><g:else>
                <g:link controller="tomcatInstance" action="stopRemote" id="${instance.id}">Stop</g:link>
             </g:else>
             </i>
             <p border='1'>
             <span class="menuButton"><g:link controller="modJkWorker" action="updateActivationWorker" params="[updateActivation:'0',route: instance.name ]">All Worker Active</g:link>
             </span>
             <p border='1'>
             <g:link controller="modJkWorker" action="updateActivationWorker" params="[updateActivation:'1',route: instance.name ]">All Worker Disabled</g:link>
             </p>
             <p border='1'>
             <g:link controller="modJkWorker" action="updateActivationWorker" params="[updateActivation:'2',route: instance.name ]">All Worker Stopped</g:link>
             </p>
             <p border='1'>
             <g:link action="show" id="${instance.id}">Info Worker</g:link>
             </p>
            </li>
        </g:each>
        </ul>
     </div>
</body>
</html>