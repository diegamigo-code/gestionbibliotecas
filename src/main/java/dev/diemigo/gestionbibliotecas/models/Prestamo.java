package dev.diemigo.gestionbibliotecas.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.aspectj.bridge.IMessage;

import java.beans.XMLEncoder;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {


    @NonNull()
    private Integer idPrestamo;
    @NonNull
    private Integer idLibro;
    @NonNull
    private String runSolicitante;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSolicitud;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrega;
    @NonNull
    private Integer cantidadDias;
    @NonNull
    private Integer multas;

}