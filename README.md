# Salesmgmt

## Introduction

**Salesmgmt** is a Java-based project that simulates a company’s internal sales management system.  
It was developed primarily as a **practice environment for migrating legacy internal systems to a modern Java architecture**.

While the system provides features for managing and tracking company revenue, its core purpose is to explore modular system design, clean architecture principles, and integration with real-world technologies.

## Purpose

- Practice migrating internal systems to a modern Java stack.
- Design a maintainable, testable system using **Hexagonal Architecture**.
- Simulate sales data management with realistic business logic.
- Integrate essential backend technologies including **Spring Boot**, **JPA**, and **OpenAPI**.

## Architecture

The project is structured based on the **Hexagonal Architecture** (also known as Ports and Adapters), which emphasizes the separation of business logic from external concerns such as databases and web frameworks.

### Key Layers:

- **Domain Layer**: Contains core business logic and domain models.


- **Application Layer**: Defines use cases and coordinates interactions between domain and external systems.


- **Adapters (Ports)**: Implement external interactions like databases, APIs, or UI without affecting the domain logic.

This architectural style makes the system modular, easier to test, and flexible to change.

## Getting Started

### Prerequisites

- Java 21
- Gradle
- MySQL 8.x

### Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/salesmgmt.git
   ```
2. Configure the database connection in `application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/salesmgmt
       username: your-username
       password: your-password
   ```
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## Software Stack

- **Java 21** – Language of implementation
- **Spring Boot 3.3.5** – Application framework
- **Spring Data JPA & Jakarta Persistence 3.1.0** – ORM and data access
- **MySQL 8.0.32** – Relational database
- **Gradle** – Build and dependency management
- **Springdoc OpenAPI 2.x** – API documentation (Swagger UI)

## Building the Project

To compile and package the application:

```bash
./gradlew build
```

## API Documentation

Once the application is running, Swagger UI is available at:

```
http://localhost:8080/salesmgmt/v1/api-docs
```

