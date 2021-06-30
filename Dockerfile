FROM openjdk:8-jdk-alpine
COPY target/juitwebkiosk-0.0.1-SNAPSHOT.jar webkiosk-api.jar
ENTRYPOINT ["java","-jar","/webkiosk-api.jar"]