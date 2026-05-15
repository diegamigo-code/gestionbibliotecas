package dev.diemigo.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequestDTO {
    @NotBlank(message = "Debe proporcionar un correo")
    private String correo;

    @NotBlank(message = "Debe proporcionarse una contraseña")
    private String contrasenia;
}