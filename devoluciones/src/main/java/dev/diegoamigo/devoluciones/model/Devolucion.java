package dev.diegoamigo.devoluciones.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prestamoId;
    private Long usuarioId;

    private LocalDate fechaDevolucionReal;
    private boolean atraso;
}