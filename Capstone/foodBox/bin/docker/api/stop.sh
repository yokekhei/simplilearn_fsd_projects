#!/usr/bin/env sh

docker kill $(docker container ls -a | grep foodbox-api | awk '{print $1}')
docker rm $(docker container ls -a | grep foodbox-api | awk '{print $1}')

exit 0
