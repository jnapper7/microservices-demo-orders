FROM openjdk:11.0.15-jre

WORKDIR /usr/src/app
COPY ./target/*.jar ./app.jar

RUN	chown -R ${SERVICE_USER}:${SERVICE_GROUP} ./app.jar

USER ${SERVICE_USER}

ENTRYPOINT ["java","-jar","./app.jar", "--port=80"]
