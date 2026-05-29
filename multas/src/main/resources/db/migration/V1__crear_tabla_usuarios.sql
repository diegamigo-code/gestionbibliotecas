CREATE TABLE multas (
                        id          BIGINT       PRIMARY KEY AUTO_INCREMENT,
                        prestamo_id BIGINT       NOT NULL,
                        monto       DOUBLE       NOT NULL,
                        motivo      VARCHAR(255) NOT NULL,
                        pagada      TINYINT(1)   NOT NULL DEFAULT 0
);