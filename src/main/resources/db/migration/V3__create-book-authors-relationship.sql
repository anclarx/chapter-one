CREATE TABLE books_authors_relationship (
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT pk_books_authors PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_bar_book FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    CONSTRAINT fk_bar_author FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);


CREATE INDEX idx_bar_author_id ON books_authors_relationship(author_id);

INSERT INTO books_authors_relationship (book_id, author_id)
SELECT id, author_id FROM books;

ALTER TABLE books DROP CONSTRAINT fk_books_author;
ALTER TABLE books DROP COLUMN author_id;

DROP INDEX IF EXISTS idx_books_author_id;
