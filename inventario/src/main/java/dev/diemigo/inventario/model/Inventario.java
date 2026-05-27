package dev.diemigo.inventario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private Long id_libro;

    @Column
    private String titulo;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(length = 20)
    private String ubicacion;
}
