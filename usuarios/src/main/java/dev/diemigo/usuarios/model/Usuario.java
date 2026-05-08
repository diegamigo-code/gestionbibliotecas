package dev.diemigo.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Usuarios")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @Column(length = 255, nullable = false, unique = true)
    private String correo;

    @Column(length = 100, nullable = false)
    private String contrasenia;

    @Column
    private boolean activo;
}