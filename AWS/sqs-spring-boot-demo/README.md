# AWS SQS Spring Boot Demo - Producer

## Project Overview

This is a **Spring Boot 3.x** application demonstrating how to build an **AWS Simple Queue Service (SQS) Producer**. 

The main objective of this project is to implement an asynchronous order processing system. The producer module exposes a RESTful API (`POST /api/orders`) that accepts incoming order requests, enriches them with system-generated fields (like `orderId` and `status`), serializes the payload into JSON, and publishes the messages reliably to an AWS SQS queue.

This architecture acts as a decoupled, scalable entry point for ingesting high-volume data, allowing consumer services to process requests at their own pace without overwhelming backend systems.

### Key Features
* **REST API Endpoint**: Exposed `POST /api/orders` to accept new order requests.
* **AWS Integration**: Seamlessly integrates with AWS SQS using the AWS SDK for Java.
* **Asynchronous Messaging**: Offloads processing of orders by pushing them into an SQS Queue for consumers to pick up.
* **Auto-configured serialization**: Leverages Jackson for converting POJO objects (`OrderMessage`) into JSON payloads before pushing to SQS.

## Prerequisites
* **Java 17+**
* **Maven 3.6+**
* An active **AWS Account** with an SQS Queue created.
* Appropriate AWS IAM credentials configured (`AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`) locally or via AWS CLI profiles.

## Technologies Used
* **Java 17**
* **Spring Boot 3.x**
* **Spring Cloud AWS** / **AWS SDK for Java v2**
* **Maven**

## Testing the Application

### 1. Using Postman
A Postman collection has been included in the root directory: `SQS_Spring_Boot_Demo.postman_collection.json`. You can import this file directly into Postman to quickly test the API endpoints.

### 2. cURL Example
A sample payload `order-request-payload.json` is provided in the project root. You can test the application using the following command:
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d @order-request-payload.json
```

**Sample Request Body:**
```json
{
  "customerId": "CUST-10042",
  "amount": 250.50
}
```

The application will auto-generate an `orderId` and set the status to `PENDING` before placing the message onto the configured SQS queue.
