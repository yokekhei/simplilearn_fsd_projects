#!/bin/bash

docker kill $(docker container ls -a | grep foodbox-db | awk '{print $1}')
docker rm $(docker container ls -a | grep foodbox-db | awk '{print $1}')

sudo rm -rf ${PWD}/db_data
mkdir -p ${PWD}/db_data

docker run -it --rm -p 3306:3306 --name foodbox-db \
 -v ${PWD}/db_data:/var/lib/mysql \
 -e MYSQL_ROOT_PASSWORD=password \
 -e MYSQL_USER=foodbox_user \
 -e MYSQL_PASSWORD=password \
 -d mysql:8.0.23

sleep 20

docker exec -i foodbox-db sh -c 'exec mysql -uroot -p"$MYSQL_ROOT_PASSWORD"' < startup.sql

exit 0
