CREATE TABLE multas (
                          id          BIGINT       PRIMARY KEY AUTO_INCREMENT,
                          correo      VARCHAR(255) UNIQUE NOT NULL,
                          contraseña VARCHAR(255) NOT NULL,
                          activo      TINYINT(1)   DEFAULT 1
);