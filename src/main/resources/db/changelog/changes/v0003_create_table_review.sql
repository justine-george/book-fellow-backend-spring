CREATE TABLE "review"
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_id         UUID NOT NULL REFERENCES "book"(id),
    content         TEXT,
    reviewed_by     UUID NOT NULL REFERENCES "user"(id) ON DELETE SET NULL,
    is_author_liked BOOLEAN DEFAULT FALSE NOT NULL,
    rating_5_scale  NUMERIC CHECK (rating_5_scale >= 0 AND rating_5_scale <= 5),
    like_count      INTEGER DEFAULT 0 NOT NULL,
    created_at      TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_spoiler      BOOLEAN DEFAULT FALSE NOT NULL,
    is_deleted      BOOLEAN DEFAULT FALSE NOT NULL,
    deleted_by      UUID REFERENCES "user"(id) ON DELETE SET NULL,
    deleted_at      TIMESTAMPTZ,
    delete_reason   VARCHAR(50),
    updated_at      TIMESTAMPTZ      DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TRIGGER update_review_modtime
    BEFORE UPDATE
    ON "review"
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();
