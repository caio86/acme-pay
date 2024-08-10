FROM openjdk:17-jdk-alpine

WORKDIR /opt/app

COPY ./target/*.jar app.jar

ENV DB_DATABASE=account-service
ENV DB_HOST=account-service-db
ENV DB_PASSWORD=postgres
ENV DB_PORT=5432
ENV DB_USER=postgres

ENV RABBITMQ_HOST=rabbitmq-acme-pay
ENV RABBITMQ_PORT=5672

ENV KAFKA_BROKER=kafka:9092

ENV SERVER_PORT=8080

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
