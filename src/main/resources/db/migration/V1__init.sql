CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE HeroAlignment AS ENUM ('Hero', 'Anti-Hero');

CREATE TABLE heroes
(
    id         UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name       VARCHAR(255)             NOT NULL,
    power      VARCHAR(255)             NOT NULL,
    alignment  HeroAlignment            NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);