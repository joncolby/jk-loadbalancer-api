#!/bin/bash

TARGET_DIR=target
app=tomcatmaster

SERVERS="lbapi45-1 lbapi46-1"
#SERVERS="lbapi45-1"
#SERVERS="10.45.22.40 10.46.22.40"


##########################
grails clean && grails war 
for host in $SERVERS ; do 
	scp $TARGET_DIR/$app.war root@$host:/opt/www/
	ssh root@$host "chown tomcat:tomcat /opt/www/${app}.war"
done
