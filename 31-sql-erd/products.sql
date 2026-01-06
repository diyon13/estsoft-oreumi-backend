CREATE TABLE products (
    id           BIGINT        NOT NULL,
    name         VARCHAR(20)   NOT NULL,
    stock        INT           DEFAULT 0,
    price        INT           NOT NULL,
    manufacturer VARCHAR(20)   NOT NULL,

    CONSTRAINT PK_ID PRIMARY KEY (id),
    CONSTRAINT CHECK_STOCK CHECK (stock >= 0 AND stock <= 10000)
);