CREATE TABLE "post"
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content      TEXT,
    posted_by    UUID NOT NULL REFERENCES "user"(id) ON DELETE SET NULL,
    like_count   INTEGER DEFAULT 0 NOT NULL,
    created_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at   TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_spoiler   BOOLEAN DEFAULT FALSE NOT NULL,
    is_deleted   BOOLEAN DEFAULT FALSE NOT NULL,
    deleted_by   UUID REFERENCES "user"(id) ON DELETE SET NULL,
    deleted_at   TIMESTAMPTZ,
    delete_reason VARCHAR(50)
);

CREATE TRIGGER update_post_modtime
    BEFORE UPDATE
    ON "post"
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();
