package dev.diegoamigo.multas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultaRespuestaDTO {
    private Long id;
    private Long prestamoId;
    private double monto;
    private String motivo;
    private boolean pagada;
}