package dev.diemigo.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @Column(nullable = false, unique = true)
    private String correo;

    @Column(name = "contrasenia",length = 100, nullable = false)
    private String contrasenia;

    @Column(name = "activo")
    private boolean activo;
}