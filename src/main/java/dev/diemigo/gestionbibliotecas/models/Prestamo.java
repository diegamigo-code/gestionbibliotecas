package dev.diemigo.gestionbibliotecas.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {


    @
    private Integer idPrestamo;
    private Integer idLibro;
    private String runSolicitante;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSolicitud;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrega;

    private Integer cantidadDias;
    private Integer multas;

}