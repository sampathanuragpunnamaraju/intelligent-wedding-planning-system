# Event Management

# Intelligent Wedding Planning System

## Overview

The Intelligent Wedding Planning System is an AI-assisted wedding planning platform designed to help customers plan weddings through structured questionnaires, business rule analysis, feasibility evaluation, and AI-generated recommendations.

The system focuses on helping users understand whether their wedding plans are realistic, affordable, and achievable.

---

## MVP Scope

### Actor

Customer

### Event Type

Wedding

---

## Core Features

### Authentication

* User Registration
* User Login
* JWT Security

### Wedding Planning

* Wedding Questionnaire
* Budget Analysis
* Guest Analysis
* Venue Analysis
* Timeline Planning

### AI Assistance

* Recommendation Generation
* Alternative Suggestions
* Explanation Generation

---

## System Workflow

Customer
↓
Login/Register
↓
Create Wedding Event
↓
Fill Questionnaire
↓
Rule Engine Analysis
↓
Feasibility Evaluation
↓
AI Recommendations
↓
Generated Wedding Plan

---

## Rule Engine Modules

### Budget Rule Engine

* Budget Allocation
* Cost Estimation
* Budget Feasibility

### Guest Rule Engine

* Capacity Planning
* Parking Requirements
* Staff Requirements

### Venue Rule Engine

* Capacity Validation
* Budget Validation
* Weather Analysis

### Timeline Rule Engine

* Task Scheduling
* Planning Milestones
* Critical Alerts

---

## Technology Stack

### Backend

* Spring Boot
* Spring Security
* Spring Data JPA

### Frontend

* React

### Database

* MySQL

### AI

* Gemini API

---

## Database Entities

### User

Stores customer account information.

### WeddingEvent

Stores questionnaire responses and wedding requirements.

### GeneratedPlan

Stores generated reports and recommendations.

---

## Current Status

Project Phase:

Architecture and Planning Complete

Next Phase:

Spring Boot Backend Development

---

## Repository Structure

EventManagement/

├── Backend/

├── Frontend/

├── docs/

├── prompts/

└── README.md

---

## Documentation

Refer to:

* docs/ProjectVision.md
* docs/Architecture.md
* docs/Questionnaire.md
* docs/Database-Design.md
* docs/Progress-Tracker.md
* docs/Current-State.md
* docs/Decisions-Log.md
* prompts/codex-context.md

for complete project details and development history.
