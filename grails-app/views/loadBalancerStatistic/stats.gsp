<g:set var="header" value="${statsArray[0]}"></g:set>
<g:each in="${header}" var="i">${i.key.toString().substring(i.key.toString().indexOf('.')+1,i.key.toString().length())} </g:each>
<g:each in="${statsArray}" var="item"><g:each in="${item}" var="map">${map.value} </g:each>
</g:each>