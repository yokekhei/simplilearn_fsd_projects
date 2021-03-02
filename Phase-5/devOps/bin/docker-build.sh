#!/usr/bin/env sh

base_dir=$(dirname $0)
docker kill $(docker container ls -a | grep devops-app | awk '{print $1}')
docker rm $(docker container ls -a | grep devops-app | awk '{print $1}')
docker rmi $(docker image ls -a | grep devops-app | awk '{print $3}')
docker build -t devops-app ${base_dir}/../

exit 0
