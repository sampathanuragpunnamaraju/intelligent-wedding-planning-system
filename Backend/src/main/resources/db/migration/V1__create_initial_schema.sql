CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users__email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE wedding_events (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    event_name VARCHAR(150) NOT NULL,
    event_date DATE NULL,
    location VARCHAR(255) NULL,
    location_decided BOOLEAN NOT NULL,
    budget DECIMAL(15,2) NOT NULL,
    guest_count INT NOT NULL,
    venue_type VARCHAR(100) NULL,
    wedding_scale VARCHAR(100) NULL,
    wedding_style VARCHAR(100) NULL,
    catering_preference VARCHAR(100) NULL,
    guests_traveling BOOLEAN NOT NULL,
    parking_importance VARCHAR(100) NULL,
    dream_wedding TEXT NULL,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT pk_wedding_events PRIMARY KEY (id),
    CONSTRAINT fk_wedding_events__users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT ck_wedding_events__budget_non_negative CHECK (budget >= 0),
    CONSTRAINT ck_wedding_events__guest_count_non_negative CHECK (guest_count >= 0),
    CONSTRAINT ck_wedding_events__status_valid CHECK (status IN ('DRAFT', 'QUESTIONNAIRE_COMPLETED', 'PLAN_GENERATED')),
    INDEX idx_wedding_events__user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE generated_plans (
    id BIGINT NOT NULL AUTO_INCREMENT,
    wedding_event_id BIGINT NOT NULL,
    budget_report JSON NOT NULL,
    guest_report JSON NOT NULL,
    venue_report JSON NOT NULL,
    timeline_report JSON NOT NULL,
    ai_recommendation_report JSON NOT NULL,
    feasibility_score DECIMAL(6,2) NOT NULL,
    generated_at DATETIME(6) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT pk_generated_plans PRIMARY KEY (id),
    CONSTRAINT fk_generated_plans__wedding_events FOREIGN KEY (wedding_event_id) REFERENCES wedding_events (id) ON DELETE CASCADE,
    CONSTRAINT uk_generated_plans__wedding_event_id UNIQUE (wedding_event_id),
    CONSTRAINT ck_generated_plans__feasibility_score_range CHECK (feasibility_score >= 0 AND feasibility_score <= 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
