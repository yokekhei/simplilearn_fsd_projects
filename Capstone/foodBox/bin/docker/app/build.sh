#!/usr/bin/env sh

base_dir=$(dirname $0)

${base_dir}/stop.sh
docker rmi $(docker image ls -a | grep foodbox-app | awk '{print $3}')
docker build -t yokekhei/foodbox-app ${base_dir}/../../../app

exit 0
