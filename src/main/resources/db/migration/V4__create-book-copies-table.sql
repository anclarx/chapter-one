CREATE TABLE book_copies (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_book_copies_book FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE
);

CREATE INDEX idx_book_copies_book_id ON book_copies(book_id);