CREATE TABLE product
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255),
    trend_id    BIGINT    NOT NULL,
    created_at  TIMESTAMP NOT NULL
);

CREATE TABLE sku
(
    code           VARCHAR(12) PRIMARY KEY,
    product_id     BIGINT NOT NULL,
    created_at     TIMESTAMP   NOT NULL,
    specifications JSON,
    quantity       INT    NOT NULL,
    price          DOUBLE NOT NULL
);

CREATE TABLE trend
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
);

INSERT INTO trend (name)
VALUES ('Intel');

INSERT INTO product (
    name,
    description,
    trend_id,
    created_at
)
VALUES
    ('Processador Intel Core i7', 'Esses processadores impulsionam PCs de alto nível com desempenho de CPU excelente para gráficos de nível dedicado e aceleração de IA.', 1, CURRENT_TIMESTAMP());

INSERT INTO sku (
    code,
    product_id,
    quantity,
    price,
    created_at
)
VALUES
    ('000000000000', 1, 0, 0.0, CURRENT_TIMESTAMP()),
    ('XXXXXXXXXXXX', 1, 0, 0.0, CURRENT_TIMESTAMP());