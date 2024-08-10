FROM openjdk:17-jdk-alpine

WORKDIR /opt/app

COPY ./target/*.jar app.jar

ENV DB_DATABASE=customer-service
ENV DB_HOST=customer-service-db
ENV DB_PASSWORD=postgres
ENV DB_PORT=5432
ENV DB_USER=postgres

ENV SERVER_PORT=8080

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
