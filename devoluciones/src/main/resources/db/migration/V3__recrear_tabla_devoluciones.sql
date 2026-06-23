DROP TABLE IF EXISTS devoluciones;

CREATE TABLE devoluciones (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    prestamo_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    fecha_devolucion_real DATE NOT NULL,
    atraso TINYINT(1) NOT NULL DEFAULT 0
);
