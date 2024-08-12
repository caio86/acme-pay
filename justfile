alias sa := start-account
alias sc := start-customer
alias sn := start-notification
alias st := start-transaction
alias sg := start-gateway

alias sall := start-all-services
alias stop := stop-all-docker

list:
  just -l

test:
  mvn test

build:
  mvn clean package

# Start docker container and account service
start-account:
  #!/usr/bin/env bash
  cd ./acme-pay-account-service
  {{start-service}}

# Start docker container and customer service
start-customer:
  #!/usr/bin/env bash
  cd ./acme-pay-customer-service
  {{start-service}}

# Start docker container and notification service
start-notification:
  #!/usr/bin/env bash
  cd ./acme-pay-notification-service
  {{start-service}}

# Start docker container and transaction service
start-transaction:
  #!/usr/bin/env bash
  cd ./acme-pay-transaction-service
  {{start-service}}

# Start docker container and gateway service
start-gateway:
  #!/usr/bin/env bash
  cd ./acme-pay-gateway-service
  mvn spring-boot:run

start-all-services:
  just sa
  just sc
  just sn
  just st
  just sg

stop-all-docker:
  parallel docker compose -f {} down ::: acme-pay-*-service/docker-compose.yml

clean-docker: stop-all-docker
  sudo git clean -fx *data

start-service := '
  export $(grep -v "^#" .env | xargs)
  docker compose up -d
'
