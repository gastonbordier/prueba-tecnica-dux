#!/bin/bash

mvn clean package -DskipTests
RESULT=$?

if [ $RESULT -eq 1 ];
then
    echo 'Error de compilacion'
    exit
fi

docker build -t equipos-api-imagen  .
docker run -p 8080:8080 equipos-api-imagen
