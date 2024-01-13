CREATE TABLE IF NOT EXISTS follower_followee
(
    follower_id UUID NOT NULL REFERENCES "user"(id),
    following_id UUID NOT NULL REFERENCES "user"(id),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (follower_id, following_id),
    CHECK (follower_id != following_id)
);

CREATE TABLE IF NOT EXISTS review_like (
    user_id UUID NOT NULL REFERENCES "user"(id),
    review_id UUID NOT NULL REFERENCES review(id),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, review_id)
);

CREATE TABLE IF NOT EXISTS post_like (
    user_id UUID NOT NULL REFERENCES "user"(id),
    post_id UUID NOT NULL REFERENCES post(id),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, post_id)
);

CREATE TABLE IF NOT EXISTS comment_like (
    user_id UUID NOT NULL REFERENCES "user"(id),
    comment_id UUID NOT NULL REFERENCES comment(id),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, comment_id)
);
