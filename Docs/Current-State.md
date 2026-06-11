# Current State

## Current Branch

main

---

## Current Phase

Spring Boot Backend Development

---

## Current Task

Backend foundation configuration complete (F1.1-F1.6) and database foundation configuration complete (D2.1-D2.3).

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

---

## Next Task

Confirm GeneratedPlan regeneration semantics (D2.4).

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
* No entities, repositories, controllers, or authentication implementation created

---

## Database Foundation

* MySQL connection variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
* Future API keys remain environment-based, including `GEMINI_API_KEY`
* Flyway migration location: `classpath:db/migration`
* Flyway owns schema changes
* Hibernate validates the schema and does not create or update it
* Database conventions are documented in `Docs/Database-Conventions.md`
* No SQL migrations or database tables have been created

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
