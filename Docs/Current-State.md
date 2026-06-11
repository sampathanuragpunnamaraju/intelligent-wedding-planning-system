# Current State

## Current Branch

main

---

## Current Phase

Phase 4 - API Foundation

---

## Current Task

A4.1-A4.6 API foundation completed; clean MySQL migration verification remains pending (D2.7).

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
* F1.1 Maven Spring Boot project generated
* F1.2 Backend foundation dependencies added
* F1.3 Base package structure created
* F1.4 Development and test configuration profiles created
* F1.5 Environment-based configuration completed for database credentials and future API keys
* F1.6 Minimal Spring Boot context-load verification added
* D2.1 MySQL connectivity configured through environment variables
* D2.2 Flyway migration support configured
* D2.3 Database conventions defined
* D2.4 GeneratedPlan regeneration semantics confirmed: update the existing GeneratedPlan
* D2.5 AI recommendation storage confirmed: store AI output inside GeneratedPlan
* D2.6 Initial Flyway schema migration created
* P3.1 User entity created
* P3.2 WeddingEvent entity created
* P3.3 GeneratedPlan entity created
* P3.4 Entity relationships mapped
* P3.5 Entity ownership mapping defined
* P3.6 WeddingEventStatus lifecycle approved
* P3.7 Repositories and persistence integration tests added
* A4.1 DTO conventions defined
* A4.2 Validation error handling added
* A4.3 Domain exception mapping added
* A4.4 Entity-to-DTO mapping boundaries defined
* A4.5 API versioning and response conventions added
* A4.6 Controller-level testing strategy added

---

## Next Task

Verify migrations against a clean MySQL database (D2.7).

---

## Backend Foundation

* Java 21
* Maven with Maven Wrapper
* Spring Boot 4.0.6
* Base package: `com.eventmanagement`
* Packages: `config`, `controller`, `dto`, `entity`, `repository`, `service`, `security`, `exception`, `util`
* Development profile: `application-dev.properties`
* Test profile: `application-test.properties`
* Minimal Spring Boot context-load test added with the `test` profile
* Entities created: `User`, `WeddingEvent`, `GeneratedPlan`
* Wedding event status enum created: `WeddingEventStatus`
* Repositories created: `UserRepository`, `WeddingEventRepository`, `GeneratedPlanRepository`
* Shared API DTO conventions created under `com.eventmanagement.dto.api`
* Shared domain exception handling created under `com.eventmanagement.exception`
* No services, business controllers, questionnaire endpoints, security configuration, or authentication implementation created

---

## API Foundation

* Standard API response envelope: `ApiResponse`
* Standard API error payload: `ApiError`
* Field-level validation error payload: `FieldValidationError`
* API version prefix constant: `ApiRoutes.API_PREFIX` set to `/api/v1`
* Domain exception base type: `DomainException`
* Shared domain exceptions: `ResourceNotFoundException`, `InvalidRequestException`
* Global exception handler maps validation failures and domain exceptions to consistent API responses
* Entity-to-DTO mapper boundary defined through `EntityDtoMapper`
* Controller-level testing strategy established with test-only dummy endpoints
* No questionnaire CRUD endpoints were implemented during API foundation

---

## Database Foundation

* MySQL connection variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
* Future API keys remain environment-based, including `GEMINI_API_KEY`
* Flyway migration location: `classpath:db/migration`
* Initial migration: `V1__create_initial_schema.sql`
* Flyway owns schema changes
* Hibernate validates the schema and does not create or update it
* Database conventions are documented in `Docs/Database-Conventions.md`
* Initial schema defines `users`, `wedding_events`, and `generated_plans`
* Clean MySQL migration verification is pending as D2.7
* JPA mappings use the Flyway schema as the source of truth

---

## Entity Mapping

* `User` maps to `users`
* `WeddingEvent` maps to `wedding_events`
* `GeneratedPlan` maps to `generated_plans`
* Relationships use lazy loading
* `GeneratedPlan` owns the one-to-one foreign key to `WeddingEvent`
* JSON report columns use Hibernate JSON mapping with `Map<String, Object>`
* Audit timestamps use `LocalDateTime`, `@CreationTimestamp`, and `@UpdateTimestamp`

---

## Repository Layer

* `UserRepository` supports user persistence and email lookup
* `WeddingEventRepository` supports wedding event persistence and lookup by user
* `GeneratedPlanRepository` supports generated plan persistence and lookup by wedding event
* Persistence integration tests cover user persistence, event ownership, one-to-one plan mapping, status persistence, and JSON persistence
* Persistence integration tests require `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`; they are skipped when those variables are not available
* API foundation tests cover response envelope, validation error handling, and domain exception mapping

---

## MVP Scope

Actor:
Customer

Event Type:
Wedding

---

## Database Entities

User

WeddingEvent

GeneratedPlan

---

## Important Constraints

* Support weddings only
* Support customer actor only
* Use business rules for calculations
* Use AI only for explanations and recommendations

---

## Repository

intelligent-wedding-planning-system
