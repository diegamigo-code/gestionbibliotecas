package dev.diegoamigo.multas.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultaDTO {

    private Long prestamoId;
    private double monto;
    private String motivo;
    private boolean pagada;
}