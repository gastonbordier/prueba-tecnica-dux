# Utiliza una imagen base con Java 17 y Maven
FROM openjdk:22 as build

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} equipos-api.jar

ENTRYPOINT ["java","-jar","/equipos-api.jar"]