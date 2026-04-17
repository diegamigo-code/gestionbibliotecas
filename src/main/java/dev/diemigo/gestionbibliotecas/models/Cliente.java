package dev.diemigo.gestionbibliotecas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @Column(unique = true)
    private int id_clientes;
    @Column(unique = true)
    private String nom_clientes;
    @Column(unique = true)
    private String email_clientes;
    @Column(unique = true)
    private String rut_cliente;
    private boolean valido;

}
