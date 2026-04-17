package dev.diemigo.gestionbibliotecas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Devolucion {
    private String codigo;
    private Integer cantidad;
    private Integer multas;

}
