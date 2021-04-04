#!/bin/bash

if [[ $# -ne 1 ]]; then
    echo "Docker tag name is required"
    exit 2
fi

TAG_NAME=$1

docker kill $(docker container ls -a | grep foodbox-api | awk '{print $1}')
docker rm $(docker container ls -a | grep foodbox-api | awk '{print $1}')
docker rmi $(docker image ls -a | grep foodbox-api | awk '{print $3}')
docker run -it --rm -d --network host -p 7070:7070 --env-file ${PWD}/env --name foodbox-api yokekhei/foodbox-api:${TAG_NAME}

exit 0
