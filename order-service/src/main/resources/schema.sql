DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    amount DOUBLE,
    order_status VARCHAR(255)
);