CREATE TABLE prestamos (
                           id              BIGINT       PRIMARY KEY AUTO_INCREMENT,
                           nombre_cliente  VARCHAR(255) NOT NULL,
                           libro           VARCHAR(255) NOT NULL,
                           fecha_prestamo  VARCHAR(50)  NOT NULL
);