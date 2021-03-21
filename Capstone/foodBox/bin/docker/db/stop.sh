#!/usr/bin/env sh

docker kill $(docker container ls -a | grep foodbox-db | awk '{print $1}')
docker rm $(docker container ls -a | grep foodbox-db | awk '{print $1}')

sudo rm -rf ${PWD}/db_data

exit 0
