CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_libro BIGINT,
    titulo VARCHAR(100),
    estado VARCHAR(30),
    ubicacion VARCHAR(80)
);