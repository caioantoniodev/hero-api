CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE hero_alignment_enum AS ENUM ('HERO', 'ANTI_HERO');

CREATE TABLE heroes
(
    id         UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name       VARCHAR(255)             NOT NULL,
    power      VARCHAR(255)             NOT NULL,
    alignment  hero_alignment_enum      NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);