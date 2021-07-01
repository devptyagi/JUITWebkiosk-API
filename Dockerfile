FROM openjdk:8-jdk-alpine
COPY target/webkiosk-api.jar webkiosk-api.jar
ENTRYPOINT ["java","-jar","/webkiosk-api.jar"]