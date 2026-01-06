-- SQLite
CREATE TABLE customers (
    id          INT         NOT NULL,
    name        VARCHAR(50) NOT NULL,
    age         INT,
    rank        VARCHAR(20) NOT NULL,
    occupation  VARCHAR(50),
    savings     INT         DEFAULT 0,

    PRIMARY KEY(id),
    CHECK (age >= 0)
);

