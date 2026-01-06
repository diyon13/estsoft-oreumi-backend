CREATE TABLE orders (
    id          BIGINT        NOT NULL,
    customer_id BIGINT        NOT NULL,
    product_id  BIGINT        NOT NULL,
    quantity    INT           NOT NULL,
    address     VARCHAR(100)  NOT NULL,
    ordered_at  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT PK_ID PRIMARY KEY (id),
    CONSTRAINT FK_CUSTOMER_ID FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT FK_PRODUCT_ID  FOREIGN KEY (product_id) REFERENCES products(id)
);