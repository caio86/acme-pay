alias se := start-eureka
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

build-docker: 
  parallel -j6 -C ":" docker build --build-arg SERVICE_NAME={1} --build-arg SERVICE_PORT={2} -t caio86/{1} . ::: {{services}}

start-eureka:
  #!/usr/bin/env bash
  set -euxo pipefail
  cd ./acme-pay-eureka-server
  {{start-service}}

# Start docker container and account service
start-account:
  #!/usr/bin/env bash
  set -euxo pipefail
  cd ./acme-pay-account-service
  {{start-service}}

# Start docker container and customer service
start-customer:
  #!/usr/bin/env bash
  set -euxo pipefail
  cd ./acme-pay-customer-service
  {{start-service}}

# Start docker container and notification service
start-notification:
  #!/usr/bin/env bash
  set -euxo pipefail
  cd ./acme-pay-notification-service
  {{start-service}}

# Start docker container and transaction service
start-transaction:
  #!/usr/bin/env bash
  set -euxo pipefail
  cd ./acme-pay-transaction-service
  {{start-service}}

# Start docker container and gateway service
start-gateway:
  #!/usr/bin/env bash
  set -euxo pipefail
  cd ./acme-pay-gateway-service
  {{start-service}}

start-all-services:
  {{start-service}}
  just se
  just sa
  just sc
  just sn
  just st
  just sg

stop-all-docker:
  parallel docker compose -f {} down ::: $(fd docker-compose.yml)

clean-docker: stop-all-docker
  sudo git clean -fx *data

services := """
acme-pay-account-service:8080 \
acme-pay-customer-service:8080 \
acme-pay-notification-service:8080 \
acme-pay-transaction-service:8080 \
acme-pay-gateway-service:8090 \
acme-pay-eureka-server:8761
"""

start-service := '
  export $(grep -v "^#" .env | xargs)
  docker compose up -d
'
