# Decisions Log

## Decision 001

Event Type:
Wedding Only

Reason:
Reduce MVP complexity and focus on one domain.

---

## Decision 002

Actor:
Customer Only

Reason:
Focus on wedding planning workflow before introducing vendors or organizers.

---

## Decision 003

AI Responsibility

AI Should:

* Explain recommendations
* Suggest alternatives
* Generate planning insights

AI Should Not:

* Calculate budgets
* Replace business rules
* Determine feasibility

Reason:
Business logic must remain deterministic and reliable.

---

## Decision 004

Rule Engine Design

Modules:

* Budget Rule Engine
* Guest Rule Engine
* Venue Rule Engine
* Timeline Rule Engine

Reason:
Separation of concerns and easier maintenance.

---

## Decision 005

Generated Plan Storage

Store reports as JSON initially.

Reason:
Faster MVP development and flexibility.

---

## Decision 006

Technology Stack

Backend:
Spring Boot

Frontend:
React

Database:
MySQL

AI:
Gemini

Reason:
Industry-standard stack with strong community support.
