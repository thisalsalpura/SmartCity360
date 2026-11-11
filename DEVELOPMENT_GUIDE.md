# SmartCity360 Development Guide

## Overview

SmartCity360 is a Java-based smart city management application built with Jakarta EE and Maven. The project packages as a WAR and exposes a REST API while also serving a set of JSP pages for browser-based monitoring and operations.

The system centers on city operations such as street-light fleet control, policy evaluation, complaints handling, energy stream monitoring, and city-zone management. It also demonstrates several object-oriented design patterns, including mediator, interpreter, flyweight, decorator, composite, and chain-of-responsibility.

## Tech Stack

- Java 21
- Maven (wrapper included: `mvnw`)
- Jakarta EE / Jakarta REST (`jakarta.ws.rs`)
- CDI (`jakarta.enterprise`)
- Bean Validation (`jakarta.validation`)
- JSP pages for the web UI
- JUnit 5 for tests
- Packaging: WAR

## Project Structure

```text
SmartCity360/
├─ pom.xml
├─ mvnw / mvnw.cmd
├─ src/
│  ├─ main/
│  │  ├─ java/com/thisal/smartcity360/
│  │  │  ├─ config/              # Runtime configuration helpers
│  │  │  ├─ dto/                 # Data transfer objects
│  │  │  ├─ enums/               # Domain enums
│  │  │  ├─ exception/           # Custom exceptions and exception mappers
│  │  │  ├─ model/               # Request payload models
│  │  │  ├─ pattern/             # Design pattern implementations
│  │  │  ├─ record/              # Immutable record models for responses
│  │  │  ├─ repo/                # In-memory repositories
│  │  │  ├─ service/             # Business logic services
│  │  │  └─ web/rest/
│  │  │      ├─ activator/       # REST bootstrap (ApplicationPath)
│  │  │      └─ resource/        # REST endpoints
│  │  ├─ resources/
│  │  └─ webapp/
│  │      ├─ index.jsp
│  │      ├─ city-zones.jsp
│  │      ├─ complaints.jsp
│  │      ├─ control-center.jsp
│  │      ├─ energy-stream.jsp
│  │      ├─ policies.jsp
│  │      └─ street-lights.jsp
│  └─ test/
└─ target/
```

## Key Modules and Responsibilities

### 1. REST API Layer

The application exposes REST endpoints under `/api` via `RestActivator`.

Resource classes in `web/rest/resource/` handle HTTP requests and validation:

- `CityZoneResource`
- `ComplaintResource`
- `ControlCenterResource`
- `EnergyStreamResource`
- `PolicyResource`
- `StreetLightResource`

These classes use CDI injection and are scoped as `@RequestScoped` or application-scoped services depending on the use case.

### 2. Service Layer

The `service` package contains the core business logic.
Examples:

- `StreetLightService` manages fleet generation, schedule control, and benchmarking
- `PolicyService` validates and evaluates policy rules using a custom interpreter
- `ControlCenterService` simulates sensor and outage events through a mediator pattern
- `ComplaintService` and `CityZoneService` support operational reporting and zone management

### 3. Repository and In-Memory State

The app uses lightweight repositories stored in memory, not a database. These are located under `repo/` and are typically CDI `@ApplicationScoped` beans.

This makes the project easy to demo and test locally while still keeping a clean service/repository separation.

### 4. Design Pattern Demonstrations

The project explicitly implements several structural and behavioral patterns under `pattern/`:

- Mediator: city control coordination
- Interpreter: policy validation and rule interpretation
- Flyweight: efficient street-light type reuse
- Decorator: data stream transformation layers
- Composite: city zone hierarchy
- Chain of Responsibility: complaint escalation workflow

This is a learning/demo application as much as a production-ready backend.

### 5. Request and Response Models

- `model/` holds request DTOs for input validation
- `record/` holds immutable response and domain objects
- `enums/` centralizes domain constants and operations
- `exception/` contains business exceptions plus JAX-RS mappers for consistent error responses

## Prerequisites

Before starting development, install:

- JDK 21
- Maven 3.9+ or use the bundled `./mvnw`
- A Jakarta EE compatible server for deployment (Payara, GlassFish, WildFly, etc.)

## Local Setup

From the project root:

```bash
cd SmartCity360
./mvnw test
./mvnw package
```

This will compile the application, run unit tests, and produce a deployable WAR file in `target/SmartCity360.war`.

## Running the Application

This project is built as a WAR and is intended to be deployed in a Jakarta EE runtime.

Typical flow:

1. Start your Jakarta EE server.
2. Deploy the generated WAR.
3. Open the application entry page, usually:
   - `http://localhost:8080/SmartCity360/`
   - or the server-specific context root configured by your runtime

The JSP pages expose the user interface for the major domains:

- `/city-zones.jsp`
- `/complaints.jsp`
- `/control-center.jsp`
- `/energy-stream.jsp`
- `/policies.jsp`
- `/street-lights.jsp`

## REST API Conventions

The application is mapped at the root path:

```text
/api
```

Example resource paths include:

- `/api/control-center/modules`
- `/api/control-center/outage`
- `/api/control-center/noise`
- `/api/control-center/time`
- `/api/policy/...`
- `/api/street-light/...`
- `/api/energy-stream/...`
- `/api/city-zone/...`
- `/api/complaint/...`

These APIs use JSON request and response payloads and rely on Jakarta Bean Validation for input checks.

## Development Workflow

Follow this workflow when adding or changing features:

1. Identify the relevant domain service.
2. Add or update request models in `src/main/java/com/thisal/smartcity360/model/` when needed.
3. Implement business logic in the corresponding service class.
4. Use the repository layer for in-memory state changes.
5. Expose functionality via the REST resource class.
6. Add validation and exception handling for invalid operations.
7. Run targeted tests and build the project.

## Testing

The project uses JUnit Jupiter and can be run with:

```bash
./mvnw test
```

For a clean verification flow:

```bash
./mvnw clean test
```

## Best Practices for This Codebase

- Keep business logic in services, not in controllers.
- Prefer immutable record-based response objects.
- Use CDI injection for dependencies.
- Validate input at the API boundary with annotations.
- Preserve the domain-specific package organization (`service`, `repo`, `pattern`, `model`, `record`).
- Keep pattern implementations independent and self-contained.

## Common Commands

```bash
# Compile the project
./mvnw compile

# Run tests
./mvnw test

# Build the WAR
./mvnw package

# Clean artifacts
./mvnw clean
```

## Notes for Contributors

This project is designed as a demonstration of smart-city systems and software design patterns rather than a large-scale enterprise persistence system. Most data is intentionally kept in memory for simplicity and fast iteration.

If you are extending the project, focus on:

- domain clarity,
- validation quality,
- proper separation between controllers, services, and repositories,
- and consistent use of the existing record/enum-based patterns.

## Summary

SmartCity360 is a Java/Jakarta EE smart-city platform centered around operations, monitoring, and policy enforcement. It combines a traditional web UI with a REST API and a rich set of design-pattern examples. For local development, the main path is Maven-based compilation and deployment to a Jakarta EE server.
