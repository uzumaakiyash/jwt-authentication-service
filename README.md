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

### Run Application

```bash
mvn spring-boot:run
```

## Future Enhancements

- Refresh Token Support
- Email Verification
- Forgot Password Flow
- Docker Integration
- Redis Token Blacklisting

## Author

Yashvanth