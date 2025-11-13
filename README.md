# BankX Transfer Service - Full Mini Project
Java 21, Spring Boot 3, MySQL, Docker, Docker Compose

## Services
- account-service (port 8081)
- transfer-service (port 8082)

## How to run locally
1. Build both services:
   ```bash
   cd bankx-project/account-service
   mvn -DskipTests package
   cd ../transfer-service
   mvn -DskipTests package
   ```
2. From project root run:
   ```bash
   docker-compose up --build
   ```
3. Endpoints:
- GET /accounts/{accountNumber} (account-service)
- PUT /accounts/update (account-service)
- POST /transfers?from=...&to=...&amount=... (transfer-service)
- GET /transfers/recent/{acc} (transfer-service)

4.Curl List:
- curl --location 'http://localhost:8081/accounts/ACC1001'
- curl --location --request POST 'http://localhost:8082/transfers?from=ACC1001&to=ACC1002&amount=1000'
- curl --location 'http://localhost:8082/transfers/recent/ACC1001'

Notes: You need Docker and Maven installed. The project uses Java 21 and Spring Boot 3.
