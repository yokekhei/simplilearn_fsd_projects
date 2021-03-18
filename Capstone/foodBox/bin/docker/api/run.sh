#!/usr/bin/env sh

base_dir=$(dirname $0)

${base_dir}/stop.sh

docker run -it --rm -d --network host -p 7070:7070 --name foodbox-api foodbox-api

exit 0