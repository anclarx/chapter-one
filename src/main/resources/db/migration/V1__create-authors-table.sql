CREATE TABLE authors (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    description TEXT
);

ALTER TABLE authors
ADD CONSTRAINT uk_authors_email UNIQUE (email);