#!/usr/bin/env sh

docker kill $(docker container ls -a | grep devops-app | awk '{print $1}')
docker rm $(docker container ls -a | grep devops-app | awk '{print $1}')
docker run -it --rm -d -p 8443:8443 devops-app

exit 0
