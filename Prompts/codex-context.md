# Codex Context

## Project Name

Intelligent Wedding Planning System

---

## MVP Scope

Actor:
Customer

Event Type:
Wedding

---

## Objective

Help customers plan weddings using:

* Questionnaire Collection
* Business Rule Engine
* Feasibility Analysis
* AI Recommendations

---

## Business Rule Modules

1. Budget Rules
2. Guest Rules
3. Venue Rules
4. Timeline Rules

---

## AI Responsibilities

AI should:

* Explain recommendations
* Suggest alternatives
* Generate planning insights

AI should not:

* Calculate budgets
* Replace business rules
* Determine feasibility independently

Business rules remain the source of truth.

---

## Technology Stack

Backend:

* Spring Boot
* Spring Security
* Spring Data JPA

Frontend:

* React

Database:

* MySQL

AI:

* Gemini

Build Tool:

* Maven

Java Version:

* 21

---

## Database Entities

User

WeddingEvent

GeneratedPlan

---

## Current Status

Completed:

* Project Vision
* Architecture Design
* Development Roadmap
* Wedding Questionnaire V1
* Budget Rulebook
* Guest Rulebook
* Venue Rulebook
* Timeline Rulebook
* Database Design V1
* Current State Document
* Progress Tracker
* Decisions Log
* Session Handoff
* README

---

## Next Phase

Spring Boot Backend Development

---

## MVP Constraints

* Support weddings only
* Support customer actor only
* No vendor module in MVP
* No payment module in MVP
* No organizer/admin module in MVP
* Focus on wedding planning workflow first

---

## Required Reading Order

When starting a new session, read:

1. README.md
2. docs/Current-State.md
3. docs/Session-Handoff.md
4. docs/Progress-Tracker.md
5. docs/Decisions-Log.md
6. docs/ProjectVision.md
7. docs/Architecture.md
8. docs/Database-Design.md

Then continue from the latest documented state.

---

## Resume Rule

When starting a new session:

1. Read all required documents.
2. Summarize:

   * Project Goal
   * Current Phase
   * Current Task
   * Next Task
3. Verify understanding.
4. Continue from the latest documented state.

Do not assume undocumented requirements.

---

## Implementation Rule

Before implementing any feature:

1. Read relevant project documents.
2. Explain the implementation plan.
3. List files that will be created or modified.
4. Identify any architectural impact.
5. Wait for approval before major structural changes.

For small and isolated changes, proceed directly.

---

## Documentation Maintenance Rule

At the end of every completed development task:

Update:

1. docs/Current-State.md
2. docs/Progress-Tracker.md
3. docs/Session-Handoff.md

Reflect:

* What was completed
* Current task
* Next task
* Important implementation decisions

Before updating:

* Read existing versions
* Preserve historical information
* Update only relevant sections

After updating:

* Include documentation updates in the same commit as code changes

---

## Git Rule

After completing a task:

1. Summarize modified files
2. Suggest a commit message
3. Suggest whether a push is recommended

Use conventional commit format.

Examples:

feat(auth): create user registration module

feat(event): create wedding event entity

feat(rule-engine): implement budget analysis

docs(state): update project progress

fix(validation): improve questionnaire validation

---

## Architecture Protection Rule

Do not modify the following documents unless explicitly requested:

* ProjectVision.md
* Architecture.md
* Database-Design.md
* Rulebook-Budget.md
* Rulebook-Guest.md
* Rulebook-Venue.md
* Rulebook-Timeline.md

These documents represent approved project architecture and business rules.

---

## End Of Task Checklist

Before marking a task complete:

* Verify implementation against requirements
* Verify documentation updates
* Verify project structure consistency
* Summarize completed work
* Suggest next task
* Suggest commit message
