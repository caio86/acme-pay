FROM openjdk:17-jdk-alpine

WORKDIR /opt/app

COPY ./target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT [ "java", "-jar", "app.jar" ]

