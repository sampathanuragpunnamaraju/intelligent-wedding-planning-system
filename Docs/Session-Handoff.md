# Session Handoff

Current Phase:
Spring Boot Backend Development

Current Task:
Repositories and database-gated persistence integration tests added (P3.7); clean MySQL migration verification remains pending (D2.7)

Next Task:
Verify migrations against a clean MySQL database (D2.7)

Important Notes:
- Wedding only
- Customer actor only
- AI explains, rules calculate
- Backend uses Java 21, Maven, and Spring Boot 4.0.6
- Base package is com.eventmanagement
- Foundation dependencies and requested package structure are present
- Development and test profiles have been created
- MySQL uses DB_URL, DB_USERNAME, and DB_PASSWORD environment variables
- Future API keys remain environment-based, including GEMINI_API_KEY
- Flyway migrations belong in Backend/src/main/resources/db/migration
- Initial migration V1__create_initial_schema.sql creates users, wedding_events, and generated_plans
- GeneratedPlan is one-to-one with WeddingEvent and is updated on regeneration
- AI recommendations are stored inside GeneratedPlan, separate from rule-engine report JSON
- Flyway owns schema changes and Hibernate uses validation only
- Entities created: User, WeddingEvent, GeneratedPlan, and WeddingEventStatus
- Entity relationships are lazy by default
- GeneratedPlan owns the wedding_event_id foreign key
- JSON report fields use Hibernate JSON mapping with Map<String, Object>
- Audit timestamps use LocalDateTime with CreationTimestamp and UpdateTimestamp
- WeddingEventStatus lifecycle has been approved
- Repositories created: UserRepository, WeddingEventRepository, and GeneratedPlanRepository
- Persistence integration tests cover repository persistence, relationships, status persistence, and JSON persistence
- Persistence integration tests require DB_URL, DB_USERNAME, and DB_PASSWORD; they are skipped when those variables are unavailable
- Minimal Spring Boot context-load verification exists under the test profile
- Database conventions are documented in Docs/Database-Conventions.md
- Clean MySQL migration verification is pending as D2.7
- No controllers, services, DTOs, API endpoints, security configuration, or authentication logic have been implemented
