CREATE TABLE "comment"
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content         TEXT,
    commented_by    UUID NOT NULL REFERENCES "user"(id) ON DELETE SET NULL,
    like_count      INTEGER DEFAULT 0 NOT NULL,
    created_at      TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at      TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_spoiler      BOOLEAN DEFAULT FALSE NOT NULL,
    review_id       UUID REFERENCES "review"(id),
    post_id         UUID REFERENCES "post"(id),
    is_deleted      BOOLEAN DEFAULT FALSE NOT NULL,
    deleted_by      UUID REFERENCES "user"(id) ON DELETE SET NULL,
    deleted_at      TIMESTAMPTZ,
    delete_reason   VARCHAR(50),
    CHECK (
        (review_id IS NOT NULL AND post_id IS NULL) OR
        (review_id IS NULL AND post_id IS NOT NULL)
    )
);

CREATE TRIGGER update_comment_modtime
    BEFORE UPDATE
    ON "comment"
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();
