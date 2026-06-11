# Session Handoff

Current Phase:
Spring Boot Backend Development

Current Task:
Backend foundation configuration complete (F1.1-F1.6) and initial database schema migration created (D2.1-D2.6)

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
- Minimal Spring Boot context-load verification exists under the test profile
- Database conventions are documented in Docs/Database-Conventions.md
- Clean MySQL migration verification is pending as D2.7
- No entities, repositories, controllers, security configuration, or authentication logic have been implemented
