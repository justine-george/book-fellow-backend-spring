CREATE TABLE IF NOT EXISTS favorites (
    user_id UUID NOT NULL REFERENCES "user"(id),
    book_id UUID NOT NULL REFERENCES book(id),
    PRIMARY KEY (user_id, book_id)
);

CREATE TABLE IF NOT EXISTS want_to_read (
    user_id UUID NOT NULL REFERENCES "user"(id),
    book_id UUID NOT NULL REFERENCES book(id),
    PRIMARY KEY (user_id, book_id)
);

CREATE TABLE IF NOT EXISTS currently_reading (
    user_id UUID NOT NULL REFERENCES "user"(id),
    book_id UUID NOT NULL REFERENCES book(id),
    PRIMARY KEY (user_id, book_id)
);

CREATE TABLE IF NOT EXISTS read_book (
    user_id UUID NOT NULL REFERENCES "user"(id),
    book_id UUID NOT NULL REFERENCES book(id),
    read_at TIMESTAMPTZ NOT NULL,
    PRIMARY KEY (user_id, book_id)
);
