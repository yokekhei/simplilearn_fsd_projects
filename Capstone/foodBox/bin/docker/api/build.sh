#!/usr/bin/env sh

base_dir=$(dirname $0)

${base_dir}/stop.sh
docker rmi $(docker image ls -a | grep foodbox-api | awk '{print $3}')
docker build -t yokekhei/foodbox-api ${base_dir}/../../../api

exit 0
