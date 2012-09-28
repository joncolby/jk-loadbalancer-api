<g:set var="context" value="${createLinkTo(dir: '/')}" />
<html>
    <head>
        <title>JK Loadbalancer REST API Help</title>
	<!-- meta name="layout" content="main" / -->
    </head>
    <body>
        <h1 style="margin-left:20px;">JK Loadbalancer REST API Help</h1>
        <p style="margin-left:20px;width:80%">
        
        This document describes the syntax and functions of the mod-jk loadbalancer REST API.   
        </p>
        <p style="margin-left:20px;width:80%">
        <b>All Apache mod-jk servers (front) must be registered in the database. </b> <br>
        Click <a href="<g:createLink controller="apacheFrontend" action="create" />">here</a> to add new front server.<br>
    
        Click <a href="<g:createLink controller="apacheFrontend" action="list" />">here</a> to list front servers already registered.
     

        </p>
        <div class="dialog" style="margin-left:20px;width:60%;">
        
        
         <h2>Dashboards:</h2>
        
        <table border="1">
        <tr>
        <td><a href="<g:createLink controller="dashboard" action="errors" />">Error Dashboard</a></td>
        </tr>
        <tr>
        <td><a href="<g:createLink controller="dashboard" action="activation" />">Activation Dashboard</a></td>
        </tr>
        <tr>
        <td><a href="<g:createLink controller="dashboard" action="queue" />">Activation Queue</a></td>
        </tr>
        </table>

        <h2>Health and Statistics:</h2>

        <table border="1">
        <tr>
        <th>function</th>
        <th>description</th>
        <th>rest syntax</th>
        </tr>

        <tr>
        <td>stats</td>
        <td>Show statistics for a modjk loadbalancer</td>
        <td>${context}stats/<b>[lbname]</b>[?netmask=ip-address]</td>
        </tr>

        <tr>
        <td>health</td>
        <td>Show the health of workers in a modjk loadbalancer</td>
        <td>${context}health/<b>[lbname]</b>[?netmask=ip-address]</td>
        </tr>

        </table>

      <p>
         <h3>Examples:</h3>

        <pre>
        ${request.getRequestURL()}health/search
        ${request.getRequestURL()}health
        ${request.getRequestURL()}health/search?netmask=10.46

        ${request.getRequestURL()}stats/search
        ${request.getRequestURL()}stats/search?netmask=10.47
        ${request.getRequestURL()}stats
        </pre>
        </p>
        
        
        <h2>List functions:</h2>
        
        <table border="1">
        <tr>
        <th>function</th>
        <th>description</th>
        <th>rest syntax</th>
        </tr>
        
        <tr>
        <td>list</td>
        <td>Show the status of all tomcat workers on <b>host</b></td>
        <td>${context}list/<b>host</b></td>
        </tr>
        
        <tr>
        <td>list instance</td>
        <td>Show the status of one tomcat worker instance</td>
        <td>${context}list/<b>host</b>?instance=<b>instance name pattern</b></td>
        </tr>
     
         <tr>
        <td>list activation</td>
        <td>Show the status of tomcat workers with certain activation</td>
        <td>${context}list/<b>host</b>?activation=<b>(ACT|DIS|STP)</b></td>
        </tr>   
        
        <tr>
        <td>list status</td>
        <td>Show the status of tomcat workers with certain error status</td>
        <td>${context}list/<b>host</b>?state=<b>(OK|ERR)</b></td>
        </tr>   
        
         <tr>
        <td>loadbalancer list workers</td>
        <td>Show all the workers in a loadbalancer group</td>
        <td>${context}listlb/<b>lbname</b></td>
        </tr>   
        
                 <tr>
        <td>loadbalancer list</td>
        <td>Show all loadbalancers and counts for workers</td>
        <td><a href="${context}listlb">${context}listlb</a> </td>
        </tr>   
        
        </table>
        
        <p>
         <h3>Examples:</h3>
        
        <pre>
       ${request.getRequestURL()}list/search45-1
       ${request.getRequestURL()}list/home45-2?instance=home45-2_i01
       ${request.getRequestURL()}list/search45-18?activation=STP
       ${request.getRequestURL()}list/oeb45-1?state=ERR
       ${request.getRequestURL()}listlb/searchlb
        </pre>
        </p>
        
        
         <h2>Activation functions:</h2>
        
        <table border="1">
        <tr>
        <th>function</th>
        <th>description</th>
        <th>rest syntax</th>
        </tr>
        
        <tr>
        <td>disable</td>
        <td>Disable all tomcat workers on <b>host</b></td>
        <td>${context}dis/<b>host-name</b></td>
        </tr>
        
        <tr>
        <td>activate</td>
        <td>Activate all tomcat workers on <b>host</b></td>
        <td>${context}act/<b>host-name</b></td>
        </tr>
        
         <tr>
        <td>stop</td>
        <td>Stop all tomcat workers on <b>host</b></td>
        <td>${context}stp/<b>host-name</b></td>
        </tr>
        
        </table>
        
        
       <p>
        <h3>Further Options:</h3>
        
		All activation commands have an optional <b>slow</b> parameter.  The value of <b>slow</b> is seconds. When this option is 
		specified, the activation on each front is delayed for a random number of seconds from <b>0 - slow seconds</b>
        

        Activate the instances on host randomly within 5 minutes:
       <pre>
       http://api-host${context}act/search45-1?slow=300
       </pre>

        Activation commands also accept the same parameters as list functions:  
        
        <ul>
        <li>instance
        <li>activation
        <li>state
        </ul>

        </p>
        
        <h2>Tomcat PING-PONG function:</h2>
        
        <table border="1">
        <tr>
        <th>function</th>
        <th>description</th>
        <th>rest syntax</th>
        </tr>
        
        <tr>
        <td>ping</td>
        <td>Send a mod-jk <a href="http://tomcat.apache.org/connectors-doc/ajp/ajpv13a.html">ping</a> request and display response.</td>
                <td>${context}ping/<b>host-name</b></td>
        </tr>
        </table>
        
        <p>

        The ping command also accept the same parameters as list functions:  
        <ul>
        <li>instance
        <li>activation
        <li>state
        </ul>
        
        
        
       <h2>Audit functions:</h2>
        
        <table border="1">
        <tr>
        <th>function</th>
        <th>description</th>
        <th>rest syntax</th>
        </tr>
        
        <tr>
        <td>list</td>
        <td>Display audit events</td>
        <td><a href="<g:createLink controller="audit" action="list" />"><g:createLink controller="audit" action="list" /></a></td>
        </tr>

        </table>

      

    </body>
</html>