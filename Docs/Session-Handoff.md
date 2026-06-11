# Session Handoff

Current Phase:
Spring Boot Backend Development

Current Task:
Backend foundation configuration complete (F1.1-F1.6) and database foundation configuration complete (D2.1-D2.3)

Next Task:
Confirm GeneratedPlan regeneration semantics (D2.4)

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
- Flyway owns schema changes and Hibernate uses validation only
- Minimal Spring Boot context-load verification exists under the test profile
- Database conventions are documented in Docs/Database-Conventions.md
- No SQL migrations or database tables have been created
- No entities, repositories, controllers, security configuration, or authentication logic have been implemented
