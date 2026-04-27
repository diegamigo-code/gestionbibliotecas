package dev.diemigo.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Este campo necesita ser rellenado")
    private int id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Este campo no puede estar vacío")
    private String nombre;

    @Column(length = 255, nullable = false)
    @NotBlank(message = "Se debe proporcionar un correo válido")
    private String email;

    @Column(length = 255, nullable = false)
    @NotNull(message = "Se debe proporcionar una contraseña")
    private String password;
}
