CREATE TABLE product (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    description VARCHAR(255),
    trend_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL
);
CREATE TABLE sku (
  code VARCHAR(12) PRIMARY KEY,
  product_id BIGINT NOT NULL,
  created_at TIME NOT NULL,
  specifications JSON,
  quantity INT NOT NULL,
  price DOUBLE NOT NULL
);
CREATE TABLE trend (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_at TIME
)