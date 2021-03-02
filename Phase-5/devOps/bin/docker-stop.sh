#!/usr/bin/env sh

docker kill $(docker container ls -a | grep devops-app | awk '{print $1}')
docker rm $(docker container ls -a | grep devops-app | awk '{print $1}')

exit 0
