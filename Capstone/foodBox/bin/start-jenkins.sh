#!/usr/bin/env sh

sudo systemctl stop tomcat
sudo systemctl stop jenkins
sudo usermod -aG docker ${USER} 
java -jar /usr/share/jenkins/jenkins.war &
