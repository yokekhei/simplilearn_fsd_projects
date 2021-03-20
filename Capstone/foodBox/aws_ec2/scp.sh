#!/bin/bash

user='ec2-user'
instance='ec2-3-84-148-148.compute-1.amazonaws.com'
homePath=/home/${user}
destination=${user}@${instance}:${homePath}
pemFile=ssh-key.pem

chmod 400 ${pemFile}
scp -i ${pemFile} setup.sh ${destination}
