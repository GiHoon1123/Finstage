# Finstage

## Introduction

**Finstage** is a Java-based financial intelligence platform that supports individual investors by providing access to financial statements, AI-based stock price prediction, and actual vs. predicted stock chart comparison.

The system is built as a **realistic MVP prototype** to validate investment-related feature ideas and learn how to structure scalable backend systems in Java.

While the long-term goal includes features like virtual investment and paid user plans, the current version focuses on delivering a **minimal but functional experience for investment analysis**.

## Purpose

- Provide realistic financial data visualizations for public companies.
- Enable stock price predictions based on fixed external variables.
- Allow users to compare predicted prices with real market data.
- Practice building scalable, testable systems using **Hexagonal Architecture**.
- Lay the foundation for a future **freemium SaaS financial service**.

## Architecture

The project follows the **Hexagonal Architecture** (a.k.a. Ports and Adapters), which cleanly separates domain logic from infrastructure and UI.

### Key Layers:

- **Domain Layer**: Core business rules (e.g. user limitations, prediction context).
- **Application Layer**: Use case orchestration and validation.
- **Adapters (Ports)**: Interfaces to database, web, or external APIs.

This architecture promotes high testability, clear responsibilities, and long-term maintainability.

## Currently Available Features

### ✅ Financial Statement Viewer
- View static financial data (e.g. sales, operating profit, ROE).
- Accessible for all users, no login required.

### ✅ Stock Price Prediction (AI)
- Predict future prices of selected companies using predefined external variables.
- Initial model: Simple linear regression.

### ✅ Real vs. Predicted Chart
- Overlay prediction results with recent actual stock prices.
- Helps users visually assess prediction reliability.

### ✅ Company Prediction Limitation (Freemium Logic)
- Registered users can choose up to **3 companies** for prediction.
- This limit simulates future upgrade logic for tiered subscription.

### ✅ User Authentication
- Basic email + password registration and login (JWT based or Firebase-ready).

> ❌ *Virtual investment and portfolio tracking are excluded from MVP.*

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

