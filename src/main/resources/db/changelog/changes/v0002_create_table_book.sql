CREATE TABLE book
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    isbn                VARCHAR(13) UNIQUE NOT NULL,
    title               VARCHAR(255)       NOT NULL,
    author              VARCHAR(255)       NOT NULL,
    year_of_publication INT,
    publisher           VARCHAR(255),
    image_url_small     VARCHAR(255),
    image_url_medium    VARCHAR(255),
    image_url_large     VARCHAR(255)
);