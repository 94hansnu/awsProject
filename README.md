# AWS Projekt

## Description
This project is a simple web service written in Spring Boot that manages Bosses och Slaves depending on your authority level. It includes CRUD functionality for handling bosses and slaves via Postman or a frondend application.

## Technologies
* Spring Boot
* MySQL Database
* GitHub Actions
* AWS

## Instructions

1. Ensure you have Java 21 installed on your machine.
2. Clone the project from the GitHub repository.
3. Configure your MySQL connection in the 'application.properties' file.
4. Open 'SpringBootApp' and run the proggram to start the application.
5. You can perform various CRUD operations in any REST client such as Postman or if you want to integrate a frondend application.

# API Documentation

## User Authentication

### Register User
- **Method:** POST
- **URL:** /auth/register
- **Description:** Register a new user
- **Payload:**
  ```json
  {
    "username": "string",
    "password": "string"
  }

### Log in User
Method: POST
URL: /auth/login
Description: Log in user
Payload:
json

{
  "username": "string",
  "password": "string"
}
