package dev.diemigo.gestionbibliotecas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(unique = true, nullable = false)
    @NotNull(message = "Cliente debe tener una identificación única")
    private int id_cliente;


    @Column(unique = true, nullable = false,  length = 100)
    private String nom_cliente;


    @Column(unique = true)
    private String email_cliente;


    @Column(unique = true)
    private String rut_cliente;

    @Column(unique = true)
    private boolean valido;

}
