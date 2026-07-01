CREATE TABLE IF NOT EXISTS proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proveedor VARCHAR(255) NOT NULL,
    direccion_proveedor VARCHAR(255) NOT NULL,
    telefono_proveedor VARCHAR(255) NOT NULL,
    email_proveedor VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

