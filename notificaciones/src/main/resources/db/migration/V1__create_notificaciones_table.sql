CREATE TABLE IF NOT EXISTS notificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dia_entrega DATETIME(6) NOT NULL,
    notificacion_titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

