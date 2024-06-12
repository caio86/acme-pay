alias sa := start-account
alias sab := start-account-bg

alias sc := start-customer
alias scb := start-customer-bg


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

# Stop background services by name
stop-bg-services service-name:
  jps | grep {{service-name}} | awk '{print $1}' | xargs kill -TERM

stop-all-docker:
  parallel docker compose -f {} down ::: acme-pay-*-service/docker-compose.yml

clean-docker: stop-all-docker
  git clean -fx */data

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
