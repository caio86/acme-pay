FROM openjdk:17-jdk-alpine

WORKDIR /opt/app

COPY ./target/*.jar app.jar

ENV DB_DATABASE=transaction-service
ENV DB_HOST=transaction-service-db
ENV DB_PASSWORD=postgres
ENV DB_PORT=5432
ENV DB_USER=postgres

ENV KAFKA_BROKER=kafka:9092

ENV EUREKA_URL=http://eureka-server:8761/eureka

ENV SERVER_PORT=8080

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
