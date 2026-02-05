CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    author_id BIGINT NOT NULL,
    publisher VARCHAR(255),
    description TEXT,
    edition INTEGER,
    pages INTEGER,
    publication_date DATE
);

ALTER TABLE books
ADD CONSTRAINT uk_books_isbn UNIQUE (isbn);

ALTER TABLE books
ADD CONSTRAINT fk_books_author
FOREIGN KEY (author_id)
REFERENCES authors (id)
ON DELETE CASCADE;

CREATE INDEX idx_books_author_id ON books(author_id);