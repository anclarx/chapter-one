CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    password_hash VARCHAR(255) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT uk_users_email UNIQUE (email);
ALTER TABLE users ADD CONSTRAINT uk_users_login UNIQUE (login);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_login ON users(login);