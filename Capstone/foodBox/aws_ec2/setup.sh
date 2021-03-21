#!/bin/bash

sudo yum update -y

# Git
sudo yum install git -y

# Java 8
sudo yum install java-1.8.0-openjdk -y

# Maven
sudo yum install maven -y

# Docker
sudo yum install -y docker

sudo usermod -aG docker $USER

sudo systemctl start docker

sudo curl -L "https://github.com/docker/compose/releases/download/1.28.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

newgrp docker
