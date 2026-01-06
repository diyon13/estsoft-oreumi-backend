CREATE TABLE customers (
    id          INT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    age         INT CHECK (age >= 0),
    rank        VARCHAR(20) NOT NULL,
    occupation  VARCHAR(50),
    savings     INT DEFAULT 0
);