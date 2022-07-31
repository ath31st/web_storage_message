FROM openjdk:17-jdk-alpine

LABEL version="1"
ENV TZ=Europe/Moscow

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} storage-0.0.1-SNAPSHOT.jar


EXPOSE 28852
ENTRYPOINT ["java","-jar","/storage-0.0.1-SNAPSHOT.jar"]