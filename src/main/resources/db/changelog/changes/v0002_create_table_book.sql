CREATE TABLE "book"
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name            VARCHAR(255)                             NOT NULL,
    description     TEXT,
    genre           VARCHAR(255)                             NOT NULL,
    sub_genre       VARCHAR(255),
    author_book     VARCHAR(255)                             NOT NULL,
    publisher       VARCHAR(255),
    published_year  INTEGER,
    thumbnail_url   TEXT,
    is_deleted      BOOLEAN                                  DEFAULT FALSE NOT NULL,
    deleted_by      UUID                                     REFERENCES "user"(id) ON DELETE SET NULL,
    deleted_at      TIMESTAMPTZ,
    delete_reason   VARCHAR(50),
    created_at      TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at      TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TRIGGER update_book_modtime
    BEFORE UPDATE
    ON "book"
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();
