CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY, -- BIGSERIAL means that the value is automatically incremented
    name TEXT NOT NULL,
    release_date DATE NOT NULL,
    unique (name)
);