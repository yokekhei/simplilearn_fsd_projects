#!/usr/bin/env sh

base_dir=$(dirname $0)

${base_dir}/stop.sh

docker run -it --rm -d -p 4200:4200 \
 --name foodbox-app \
 --network host \
 yokekhei/foodbox-app

exit 0
