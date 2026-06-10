# Database Conventions

## Purpose

These conventions define how MySQL schemas and Flyway migrations are created for the Intelligent Wedding Planning System.

---

## Schema Management

* Flyway is the only tool permitted to create or modify the database schema.
* Hibernate must use `ddl-auto=validate`; `create`, `create-drop`, and `update` are not permitted.
* Spring SQL initialization through `schema.sql` or `data.sql` is disabled.
* Flyway clean operations remain disabled.
* The recommended database name is `intelligent_wedding_planning`.

---

## Runtime Configuration

The backend reads database connection details from environment variables:

* `DB_URL`: Complete JDBC URL for the MySQL database.
* `DB_USERNAME`: Application database user.
* `DB_PASSWORD`: Application database password.

Credentials and environment-specific connection values must not be committed to the repository.

Example JDBC URL format:

`jdbc:mysql://localhost:3306/intelligent_wedding_planning?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`

Production environments must choose SSL options appropriate for their database deployment.

---

## Naming

* Database identifiers use lowercase `snake_case`.
* Table names are plural, such as `users`, `wedding_events`, and `generated_plans`.
* Primary key columns are named `id`.
* Foreign key columns use the singular referenced name followed by `_id`, such as `user_id`.
* Boolean columns use positive, descriptive names such as `location_decided`.
* Avoid MySQL reserved words as identifiers.
* Keep identifiers within MySQL's 64-character limit.

Constraint and index names use:

* Primary key: `pk_<table>`
* Foreign key: `fk_<table>__<referenced_table>`
* Unique constraint: `uk_<table>__<column>`
* Check constraint: `ck_<table>__<rule>`
* Index: `idx_<table>__<column_list>`

---

## Data Types

* Entity identifiers use `BIGINT`.
* Monetary values use `DECIMAL(15,2)` and never floating-point types.
* Counts use integer types and must not accept negative values.
* Timestamps use `DATETIME(6)` and are stored in UTC.
* Standard audit columns are `created_at` and `updated_at`.
* Boolean values use MySQL `BOOLEAN`, represented internally as `TINYINT(1)`.
* Domain status values use `VARCHAR` rather than native MySQL `ENUM`.
* JSON report payloads use MySQL `JSON`.
* Text columns must have an explicit maximum length or deliberately use `TEXT`.

---

## Character Encoding

* Databases and tables use the `utf8mb4` character set.
* The preferred MySQL 8 collation is `utf8mb4_0900_ai_ci`.
* Application and database connections use UTC for date and time handling.

---

## Nullability And Relationships

* Every column must declare nullability explicitly.
* Required relationships use non-null foreign keys.
* Optional relationships require a documented business reason.
* Foreign keys must define intentional delete behavior; cascading deletes are not assumed.
* Foreign key columns and frequently queried filter columns must be indexed.

---

## Flyway Migrations

Migration files belong in:

`Backend/src/main/resources/db/migration`

Versioned migrations use:

`V<version>__<lowercase_snake_case_description>.sql`

Example:

`V1__create_users_table.sql`

Rules:

* Versions increase monotonically.
* Each migration contains one coherent schema change.
* Applied migrations are immutable; corrections require a new migration.
* Migrations must be deterministic and safe on a clean database.
* Schema object names must follow this document.
* Repeatable migrations are not used during the MVP unless explicitly approved.
* MySQL DDL may auto-commit, so migrations should remain small and focused.

---

## Ownership

* Flyway migrations define the physical schema.
* JPA mappings must match the migrated schema.
* Entities, repositories, and application behavior are introduced only in their dedicated implementation phases.
