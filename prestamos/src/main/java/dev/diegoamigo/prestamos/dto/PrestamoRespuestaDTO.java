package dev.diegoamigo.prestamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoRespuestaDTO {
    private Long id;
    private String nombreCliente;
    private String libro;
    private String fechaPrestamo;
}