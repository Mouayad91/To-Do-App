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

# Application Setup
1. Clone the repository

-git clone https://github.com/Mouayad91/To-Do-App

2. Navigate to the project directory

-cd todo-app

3. Build the project using Maven

-mvn clean install

4. Run the application

-mvn clean spring-boot:run

# Endpoints

# The application has the following endpoints:

1. Authentication

-POST /api/auth/register - Register a new user
-POST /api/auth/login - Log in a user

2. ToDo

-POST /api/todos - Create a new ToDo (Admin only)
-GET /api/todos/{id} - Get a ToDo by ID (Admin and User)
-GET /api/todos - Get all ToDos (Admin and User)
-PUT /api/todos/{id} - Update a ToDo by ID (Admin only)
-DELETE /api/todos/{id} - Delete a ToDo by ID (Admin only)
-PATCH /api/todos/{id}/complete - Mark a ToDo as complete (Admin and User)
-PATCH /api/todos/{id}/incomplete - Mark a ToDo as incomplete (Admin and User)

# Using Postman

1. Register a User:

URL: http://localhost:8081/api/auth/register
Method: POST
Body (JSON):

{
    "name": "admin",
    "username": "admin",
    "email": "admin@gmail.com",
    "password": "password"
}

2. Login to Get JWT Token:

URL: http://localhost:8081/api/auth/login
Method: POST
Body (JSON):

{
    "usernameOrEmail": "admin",
    "password": "password"
}

"*** Copy the accessToken from the response.**"

3. Create a ToDo:

URL: http://localhost:8081/api/todos
Method: POST
Headers:
Authorization: Bearer <your-access-token>
Body (JSON):

{
    "title": "Sample ToDo",
    "description": "This is a sample ToDo item",
    "completed": false
}


# Acknowledgements
-Spring Boot
-Spring Security
-JSON Web Tokens (JWT)
-PostgreSQL
-Lombok