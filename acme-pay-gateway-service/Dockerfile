FROM openjdk:17-jdk-alpine

WORKDIR /opt/app

COPY ./target/*.jar app.jar

ENV EUREKA_URL=http://eureka-server:8761/eureka

EXPOSE 8090

ENTRYPOINT [ "java", "-jar", "app.jar" ]

