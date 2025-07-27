# refund-service
Refund Status Service

## Overview
The Refund Service is a Spring Boot application designed to manage and provide tax refund status for users. It integrates with external services like IRS and AI-based prediction models to fetch and predict refund details. The service uses DynamoDB as its database and follows best practices for error handling and logging.

---

## Features
- Fetch tax return details for a user.
- Provide refund status based on tax return data.
- Predict refund dates using AI models for pending refunds.
- Centralized error handling with `@ControllerAdvice`.
- Logging using Log4j2 for better traceability.

---

## Technologies Used
- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Maven**: Dependency management and build tool.
- **DynamoDB**: Database for storing tax return data.
- **Log4j2**: Logging framework.
- **AI Prediction Service**: For predicting refund dates.
- **REST API**: Exposes endpoints for refund status.

---

## Prerequisites
- **Java 17** or higher
- **Maven 3.8+**
- **AWS DynamoDB** (local or cloud setup)
- **Log4j2** configuration file (`log4j2.xml`)

---

## Installation and Setup

### 1. Clone the Repository
```bash
git clone https://github.com/your-repo/refund-service.git
cd refund-service
```

### 2. Configure Dependencies
Ensure all dependencies are defined in the `pom.xml` file. Run the following command to download them:
```bash
mvn clean install
```

### 3. Configure DynamoDB
Set up DynamoDB and ensure the `TaxReturn` table is created with the required schema.
Run localstack 
```bash
cd ./localstack
docker-compose up
```

### 4. Configure Log4j2
Add a `log4j2.xml` file in the `src/main/resources` directory for logging configuration.

### 5. Run the Application
Start the application using:
```bash
mvn spring-boot:run
```

---

## API Endpoints

### 1. Get Refund Status
**Endpoint**: `GET /v1/tax/refund/status/user/{userId}`  
**Description**: Fetches the refund status for a given user.

**Example**: `http://localhost:9080/v1/tax/refund/status/user/user123`

**Response**:  
- **200 OK**: Refund status details.
- **404 Not Found**: If the tax return is not found.
- **400 Bad Request**: If the user ID is invalid.

---

## Error Handling
The application uses a centralized exception handler (`RefundServiceExceptionHandler`) to handle errors and return meaningful responses:
- **ResourceNotFoundException**: Returns a 404 status.
- **IllegalArgumentException**: Returns a 400 status.
- **Generic Exceptions**: Returns a 500 status.

---

## Logging
The application uses Log4j2 for logging. Logs are written to both the console and a file (`logs/application.log`).

---

## Directory Structure
```
src/main/java/com/turbotax/refundservice/
├── controller/         # REST controllers
├── service/            # Business logic
├── dao/                # Data access layer
├── model/              # Data models
├── exception/          # Custom exceptions and handlers
├── utils/              # Utility classes
```

---

## Contributing
1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Submit a pull request.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
