# Basic Spring Boot Task API

## Overview

This is a simple RESTful API built with Spring Boot for managing tasks. It provides basic CRUD (Create, Read, Update, Delete) operations.

Each task includes a title, description, completion status, and creation date.

This project is intended for learning purposes and demonstrates core Spring Boot concepts such as REST controllers, service layers, repository pattern, DTO usage, dependency injection, and Java Streams.

> **What changed in this version:** tasks are now persisted in a PostgreSQL database instead of an in-memory list. The schema is managed automatically by Flyway, and a Docker Compose file is included to spin up the database locally.

![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen)
![License](https://img.shields.io/badge/License-Educational-blue)
---
## Architecture

The project follows a simple layered architecture:

### Main Layers

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Interacts with the database using Spring Data JPA (`JpaRepository`)
- **Model Layer**: Defines the core domain entities
- **DTO Layer**: Manages request and response data transfer
- **Database Layer**: PostgreSQL managed via Docker Compose; schema versioned with Flyway migrations
---

## Main technologies
This project uses the following libraries and dependencies:
- Java 25
- Spring Boot 4.0.6
- Spring Web (REST API)
- Spring Data JPA (Hibernate)
- PostgreSQL 16
- Flyway (schema migrations)
- Docker / Docker Compose
- Spring Boot DevTools
- Lombok
- Bean Validation (Hibernate Validator)
- Spring Boot Test (JUnit + Mockito)

---
## Project Structure

```text
basic-springboot-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/learn/basicspringbootapi/
│   │   │       ├── controller/
│   │   │       │   └── TaskController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   ├── TaskRequestDto.java
│   │   │       │   └── TaskResponseDto.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   └── Task.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   └── TaskRepository.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── TaskService.java
│   │   │       │   └── TaskServiceImpl.java
│   │   │       │
│   │   │       └── BasicspringbootapiApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   │           └── V1__create_tasks_table.sql
│   │
│   └── test/
│       └── java/
│           └── edu/learn/basicspringbootapi/
│               └── TaskServiceImplTest.java
│
├── docker-compose.yml
├── .env.example
├── pom.xml
└── README.md
```
---
## Features

- Create a new task
- Retrieve a task by ID
- Retrieve all tasks
- Update an existing task
- Delete a task
- PostgreSQL persistent storage
- Database schema managed with Flyway migrations
- Docker Compose setup for local database
---
## Task Model

The main entity of the application is `Task`:

```java
public class Task {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate createdAt;
}
```
---
## How to Run

### 1. Clone the repository
```bash
git clone https://github.com/Sebasjc19/basic-springboot-restapi.git
cd basic-springboot-api
```
### 2. Set up the database
```bash
cp .env.example .env
docker-compose up -d
```
### 3. Build the project
```bash
mvn clean install
```
### 4. Run the application
```bash
mvn spring-boot:run
```
---
## API Endpoints

### Create Task
```http
POST /tasks
```

### Get All Tasks
```http
GET /tasks
```

### Get Task by ID
```http
GET /tasks/{id}
```

### Update Task
```http
PUT /tasks/{id}
```

### Delete Task
```http
DELETE /tasks/{id}
```
---
## Notes

- Data is persisted in PostgreSQL and survives application restarts
- Requires a running PostgreSQL instance — use `docker-compose up -d` to start it locally
- Database schema is managed automatically by Flyway on startup
- Configure credentials via `.env` file (see `.env.example`)
- Designed for educational purposes

