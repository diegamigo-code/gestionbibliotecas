package dev.diegoamigo.prestamos.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoDTO {
    private String nombreCliente;
    private String libro;
    private String fechaPrestamo;
}