# JWT Authentication Service

Secure authentication and authorization backend application built using Spring Boot and Spring Security with JWT-based authentication.

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Maven

## Features

- User Registration
- User Login Authentication
- JWT Token Generation
- Role-Based Authorization
- Protected REST APIs
- Password Encryption using BCrypt

## Project Structure

```text
src/main/java/com/yash/authservice
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── util
```

### Authentication APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/signup | Register new user |
| POST | /api/auth/login | Authenticate user |

### Protected APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/user/profile | User protected endpoint |
| GET | /api/admin/dashboard | Admin protected endpoint |

### Run Application

```bash
mvn spring-boot:run
```

## Code Quality & Coverage

This project integrates with SonarQube and JaCoCo for static code analysis and test coverage reporting.

### Run SonarQube Analysis

Make sure SonarQube is running locally on port `9000`.

Run the following command:

```bash
mvn clean verify sonar:sonar "-Dsonar.token=YOUR_TOKEN"
```

### SonarQube Dashboard

```text
http://localhost:9000
```

## Future Enhancements

- Refresh Token Support
- Email Verification
- Forgot Password Flow
- Docker Integration
- Redis Token Blacklisting

## Author

Yashvanth Muralidaran