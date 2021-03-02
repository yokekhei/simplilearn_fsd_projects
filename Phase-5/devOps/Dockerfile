FROM maven:3.6.3-openjdk-8

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY target/devOps.war /usr/src/app

EXPOSE 8443

CMD [ "java", "-jar", "devOps.war" ]
