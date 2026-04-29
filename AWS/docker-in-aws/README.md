# Spring Boot ECS Fargate Demo

Simple Spring Boot REST API for demonstrating Docker deployment on AWS ECS with Fargate.

## Endpoint

```text
GET /api/hello
```

Example response:

```json
{
  "message": "Hello from Spring Boot on ECS Fargate",
  "application": "ecs-fargate-demo",
  "timestamp": "2026-04-18T10:00:00Z"
}
```

Health endpoint for load balancer checks:

```text
GET /actuator/health
```

## Run Locally

```powershell
mvn spring-boot:run
```

Then open:

```text
http://localhost:8080/api/hello
```

## Build Jar

```powershell
mvn clean package
```

## Build Docker Image

```powershell
docker build -t ecs-fargate-demo:latest .
```

## Run Docker Container

```powershell
docker run --rm -p 8080:8080 ecs-fargate-demo:latest
```
