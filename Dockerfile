FROM maven:3.8.7-eclipse-temurin-17-alpine as deps

WORKDIR /opt/app

COPY pom.xml .

COPY acme-pay-account-service/pom.xml acme-pay-account-service/pom.xml
COPY acme-pay-customer-service/pom.xml acme-pay-customer-service/pom.xml
COPY acme-pay-notification-service/pom.xml acme-pay-notification-service/pom.xml
COPY acme-pay-transaction-service/pom.xml acme-pay-transaction-service/pom.xml
COPY acme-pay-gateway-service/pom.xml acme-pay-gateway-service/pom.xml
COPY acme-pay-eureka-server/pom.xml acme-pay-eureka-server/pom.xml

RUN mvn -B -e -C dependency:go-offline

FROM maven:3.8.7-eclipse-temurin-17-alpine as builder

WORKDIR /opt/app

COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app

COPY acme-pay-account-service/src acme-pay-account-service/src
COPY acme-pay-customer-service/src acme-pay-customer-service/src
COPY acme-pay-notification-service/src acme-pay-notification-service/src
COPY acme-pay-transaction-service/src acme-pay-transaction-service/src
COPY acme-pay-gateway-service/src acme-pay-gateway-service/src
COPY acme-pay-eureka-server/src acme-pay-eureka-server/src

RUN mvn -B -e clean package -DskipTests=true

FROM openjdk:17-jdk-alpine

ARG SERVICE_NAME

ARG SERVICE_PORT

WORKDIR /opt/app

COPY --from=builder /opt/app/${SERVICE_NAME}/target/*.jar app.jar

ENV DB_DATABASE=account-service
ENV DB_HOST=account-service-db
ENV DB_PASSWORD=postgres
ENV DB_PORT=5432
ENV DB_USER=postgres

ENV RABBITMQ_HOST=rabbitmq-acme-pay
ENV RABBITMQ_PORT=5672

ENV KAFKA_BROKER=kafka:9092

ENV EUREKA_URL=http://eureka-server:8761/eureka

ENV SERVER_PORT=${SERVICE_PORT}

EXPOSE ${SERVICE_PORT}

ENTRYPOINT [ "java", "-jar", "app.jar" ]

