FROM eclipse-temurin:11
WORKDIR /usr/src/app
COPY ./target/*.jar application.jar

ARG COMMIT_HASH
ARG VERSION
ARG BUILD_TIMESTAMP

EXPOSE 80
CMD ["--port=80"]
ENTRYPOINT ["java", "-jar", "application.jar"]