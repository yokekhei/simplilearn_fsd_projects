#!/bin/bash

user='ec2-user'
instance='ec2-52-3-253-224.compute-1.amazonaws.com'
homePath=/home/${user}
destination=${user}@${instance}:${homePath}
pemFile=ssh-key.pem

chmod 400 ${pemFile}
scp -i ${pemFile} setup.sh ${destination}
