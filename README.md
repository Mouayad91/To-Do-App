# ToDo Application

## Overview

This is a simple ToDo application built using Spring Boot, Spring Security, JWT for authentication, and PostgreSQL for the database. The application allows users to register, log in, and manage their ToDo items.

## Features

- User Registration and Authentication
- JWT-based Authentication
- Role-based Authorization (Admin and User roles)
- CRUD operations for ToDo items
- Error Handling

## Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- PostgreSQL
- Hibernate (JPA)
- Lombok

## Prerequisites

- Java 17 or later
- Maven
- PostgreSQL

## Getting Started

### Database Setup

1. Install PostgreSQL and create a database named `toDo`.

    ```sql
    CREATE DATABASE toDo;
    ```

2. Update the `application.properties` file with your PostgreSQL credentials.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/toDo
    spring.datasource.username=your_postgres_username
    spring.datasource.password=your_postgres_password
    ```

### Application Setup

1. Clone the repository

    ```bash
    git clone https://github.com/Mouayad91/To-Do-App.git
    ```

2. Navigate to the project directory

    ```bash
    cd todo-app
    ```

3. Build the project using Maven

    ```bash
    mvn clean install
    ```

4. Run the application

    ```bash
    mvn spring-boot:run
    ```

### Endpoints

The application has the following endpoints:

#### Authentication

- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Log in a user

#### ToDo

- `POST /api/todos` - Create a new ToDo (Admin only)
- `GET /api/todos/{id}` - Get a ToDo by ID (Admin and User)
- `GET /api/todos` - Get all ToDos (Admin and User)
- `PUT /api/todos/{id}` - Update a ToDo by ID (Admin only)
- `DELETE /api/todos/{id}` - Delete a ToDo by ID (Admin only)
- `PATCH /api/todos/{id}/complete` - Mark a ToDo as complete (Admin and User)
- `PATCH /api/todos/{id}/incomplete` - Mark a ToDo as incomplete (Admin and User)

### Using Postman

1. **Register a User:**
    - URL: `http://localhost:8081/api/auth/register`
    - Method: `POST`
    - Body (JSON):
      ```json
      {
          "name": "admin",
          "username": "admin",
          "email": "admin@gmail.com",
          "password": "password"
      }
      ```

2. **Login to Get JWT Token:**
    - URL: `http://localhost:8081/api/auth/login`
    - Method: `POST`
    - Body (JSON):
      ```json
      {
          "usernameOrEmail": "admin",
          "password": "password"
      }
      ```
    - Copy the `accessToken` from the response.

3. **Create a ToDo:**
    - URL: `http://localhost:8081/api/todos`
    - Method: `POST`
    - Headers:
      - `Authorization`: `Bearer <your-access-token>`
    - Body (JSON):
      ```json
      {
          "title": "Learn C++",
          "description": "It is very important to develope amazing video games",
          "completed": false
      }
      ```

## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JSON Web Tokens (JWT)](https://jwt.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Lombok](https://projectlombok.org/)
