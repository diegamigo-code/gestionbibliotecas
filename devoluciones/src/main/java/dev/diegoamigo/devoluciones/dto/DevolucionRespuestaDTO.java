package dev.diegoamigo.devoluciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionRespuestaDTO {
    private Long id;
    private Long prestamoId;
    private Long usuarioId;
    private LocalDate fechaDevolucionReal;
    private boolean atraso;
}