#!/usr/bin/env sh

base_dir=$(dirname $0)

sudo killall mysqld

sleep 5

${base_dir}/stop.sh

mkdir -p ${PWD}/db_data

cp ${base_dir}/../../../db/startup.sql ${base_dir}

docker run -it --rm -p 3306:3306 --name foodbox-db \
 -v ${PWD}/db_data:/var/lib/mysql \
 -v ${PWD}/startup.sql:/docker-entrypoint-initdb.d/startup.sql \
 -e MYSQL_ROOT_PASSWORD=password \
 -e MYSQL_USER=foodbox_user \
 -e MYSQL_PASSWORD=password \
 -d mysql:8.0.23

exit 0
