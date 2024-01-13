CREATE TABLE IF NOT EXISTS reading_list (
    id UUID PRIMARY KEY,
    author UUID NOT NULL REFERENCES "user"(id),
    name VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TRIGGER update_reading_list_modtime
    BEFORE UPDATE
    ON reading_list
    FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

CREATE TABLE IF NOT EXISTS reading_list_item (
    list_id UUID NOT NULL REFERENCES reading_list(id),
    book_id UUID NOT NULL REFERENCES book(id),
    added_at TIMESTAMP NOT NULL,
    PRIMARY KEY (list_id, book_id)
);
