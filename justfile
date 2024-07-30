alias sa := start-account
alias sab := start-account-bg

alias sc := start-customer
alias scb := start-customer-bg

alias sn := start-notification
alias snb := start-notification-bg

alias st := start-transaction
alias stb := start-transaction-bg

alias sg := start-gateway
alias sgb := start-gateway-bg

list:
  just -l

# Start docker container and account service
start-account:
  #!/usr/bin/env bash
  cd ./acme-pay-account-service
  {{start-service}}

# Start docker container and account service as a bg job
start-account-bg:
  #!/usr/bin/env bash
  cd ./acme-pay-account-service
  {{start-service-bg}}

# Start docker container and customer service
start-customer:
  #!/usr/bin/env bash
  cd ./acme-pay-customer-service
  {{start-service}}

# Start docker container and customer service as a bg job
start-customer-bg:
  #!/usr/bin/env bash
  cd ./acme-pay-customer-service
  {{start-service-bg}}

# Start docker container and notification service
start-notification:
  #!/usr/bin/env bash
  cd ./acme-pay-notification-service
  {{start-service}}

# Start docker container and notification service as a bg job
start-notification-bg:
  #!/usr/bin/env bash
  cd ./acme-pay-notification-service
  {{start-service-bg}}

# Start docker container and transaction service
start-transaction:
  #!/usr/bin/env bash
  cd ./acme-pay-transaction-service
  {{start-service}}

# Start docker container and transaction service as a bg job
start-transaction-bg:
  #!/usr/bin/env bash
  cd ./acme-pay-transaction-service
  {{start-service-bg}}

# Start docker container and gateway service
start-gateway:
  #!/usr/bin/env bash
  cd ./acme-pay-gateway-service
  mvn spring-boot:run

# Start docker container and gateway service as a bg job
start-gateway-bg:
  #!/usr/bin/env bash
  cd ./acme-pay-gateway-service
  mvn spring-boot:run >> service.log  2>&1 &

start-all-services:
  just sab
  just scb
  just snb
  just stb
  just sgb

# Stop background services by name
stop-bg-services service-name:
  jps | grep {{service-name}} | awk '{print $1}' | xargs kill -TERM

stop-all-docker:
  parallel docker compose -f {} down ::: acme-pay-*-service/docker-compose.yml

clean-docker: stop-all-docker
  sudo git clean -fx */data

start-service := '
  export $(grep -v "^#" .env | xargs)
  docker compose up -d
  mvn spring-boot:run
'

start-service-bg := '
  export $(grep -v "^#" .env | xargs)
  docker compose up -d
  mvn spring-boot:run >> service.log  2>&1 &
  echo "spring-boot is running"
'
