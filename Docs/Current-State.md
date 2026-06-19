# Current State

## Current Branch

main

---

## Current Phase

Phase 4 - Questionnaire APIs

---

## Current Task

Q1.1 Create Wedding Event API

---

## Completed

* Git Repository Setup
* GitHub Repository Setup
* Project Vision
* Architecture Design
* Questionnaire Design
* Budget Rulebook
* Guest Rulebook
* Venue Rulebook
* Timeline Rulebook
* Database Design V1

### Backend Foundation

* F1.1 Maven Spring Boot project generated
* F1.2 Backend foundation dependencies added
* F1.3 Base package structure created
* F1.4 Development and test configuration profiles created
* F1.5 Environment-based configuration completed for database credentials and future API keys
* F1.6 Minimal Spring Boot context-load verification added

### Database Foundation

* D2.1 MySQL connectivity configured through environment variables
* D2.2 Flyway migration support configured
* D2.3 Database conventions defined
* D2.4 GeneratedPlan regeneration semantics confirmed
* D2.5 AI recommendation storage confirmed
* D2.6 Initial Flyway schema migration created
* D2.7 Clean MySQL migration verification completed

### Persistence Layer

* P3.1 User entity created
* P3.2 WeddingEvent entity created
* P3.3 GeneratedPlan entity created
* P3.4 Entity relationships mapped
* P3.5 Entity ownership mapping defined
* P3.6 WeddingEventStatus lifecycle approved
* P3.7 Repositories and persistence integration tests added

### API Foundation

* A4.1 DTO conventions defined
* A4.2 Validation error handling added
* A4.3 Domain exception mapping added
* A4.4 Entity-to-DTO mapping boundaries defined
* A4.5 API versioning and response conventions added
* A4.6 Controller-level testing strategy added

### Authentication

* A5.1 JWT Authentication implemented
* A5.2 User Registration API implemented
* A5.3 User Login API implemented
* A5.4 BCrypt Password Hashing implemented
* A5.5 JWT Security Filter implemented
* A5.6 Authentication Tests implemented
* A5.7 Security Configuration implemented

---

## Next Task

Q1.1 Create Wedding Event API

---

## Backend Foundation

* Java 21

* Maven with Maven Wrapper

* Spring Boot 4.0.6

* Base package: `com.eventmanagement`

* Packages:

  * config
  * controller
  * dto
  * entity
  * repository
  * service
  * security
  * exception
  * util

* Development profile: `application-dev.properties`

* Test profile: `application-test.properties`

---

## Database Foundation

* MySQL connection variables:

  * DB_URL
  * DB_USERNAME
  * DB_PASSWORD

* Future API keys remain environment-based:

  * GEMINI_API_KEY

* JWT values remain environment-based:

  * JWT_SECRET
  * JWT_EXPIRATION_MINUTES

* Flyway migration location:

  * classpath:db/migration

* Initial migration:

  * V1__create_initial_schema.sql

* Flyway owns schema changes

* Hibernate validates schema mappings

* Clean MySQL migration verification completed successfully

### Verified Database Tables

* flyway_schema_history
* users
* wedding_events
* generated_plans

---

## Entity Mapping

### User

Maps to:

users

### WeddingEvent

Maps to:

wedding_events

### GeneratedPlan

Maps to:

generated_plans

Relationships:

* User → One To Many WeddingEvent
* WeddingEvent → One To One GeneratedPlan

Additional Rules:

* Relationships use lazy loading
* GeneratedPlan owns wedding_event_id
* JSON reports use Hibernate JSON mapping
* Audit timestamps use LocalDateTime with CreationTimestamp and UpdateTimestamp

---

## Repository Layer

### UserRepository

Supports:

* Email lookup
* User persistence

### WeddingEventRepository

Supports:

* Event persistence
* Lookup by user

### GeneratedPlanRepository

Supports:

* Generated plan persistence
* Lookup by wedding event

Persistence integration tests cover:

* User persistence
* Event ownership
* One-to-one plan mapping
* Status persistence
* JSON persistence

---

## API Foundation

Implemented:

* ApiResponse
* ApiError
* FieldValidationError
* ApiRoutes
* EntityDtoMapper
* DomainException
* ResourceNotFoundException
* InvalidRequestException
* GlobalExceptionHandler

API Version:

/api/v1

---

## Authentication

Implemented:

### Controllers

* AuthController

### Services

* AuthService

### Security

* SecurityConfig
* JwtService
* JwtAuthenticationFilter
* JwtAuthenticationEntryPoint
* JwtAccessDeniedHandler
* DatabaseUserDetailsService
* AuthenticatedUser

### Endpoints

POST /api/v1/auth/register

POST /api/v1/auth/login

### Features

* User Registration
* User Login
* BCrypt Password Hashing
* JWT Generation
* JWT Validation
* Protected API Routing

---

## Testing Status

Latest Verification:

* Tests Run: 22
* Failures: 0
* Errors: 0
* Build Status: SUCCESS

---

## MVP Scope

Actor:

Customer

Event Type:

Wedding

---

## Database Entities

* User
* WeddingEvent
* GeneratedPlan

---

## Important Constraints

* Support weddings only
* Support customer actor only
* Use business rules for calculations
* Use AI only for explanations and recommendations

---

## Current Project Status

Completed:

* Foundation
* Database Foundation
* Persistence Layer
* API Foundation
* Authentication

Not Started:

* Questionnaire APIs
* Rule Engine
* Gemini Integration
* Frontend Development

---

## Repository

intelligent-wedding-planning-system
