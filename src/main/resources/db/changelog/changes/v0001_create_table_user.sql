CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE "user"
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255)                               NOT NULL,
    last_name  VARCHAR(255)                               NOT NULL,
    email      VARCHAR(255)                               NOT NULL UNIQUE,
    password   VARCHAR(255)                               NOT NULL,
    role       VARCHAR(255)                               NOT NULL DEFAULT 'USER' CHECK (role IN ('USER', 'ADMIN')),
    created_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE OR REPLACE FUNCTION update_modified_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_user_modtime
    BEFORE UPDATE
    ON "user"
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();