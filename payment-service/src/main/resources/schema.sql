DROP TABLE IF EXISTS payments;

CREATE TABLE payments (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    amount DOUBLE,
    payment_status VARCHAR(255),
    transaction_id VARCHAR(255)
);
