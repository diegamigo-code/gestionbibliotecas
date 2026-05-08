package dev.diemigo.usuarios.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRespuestaDTO {

    @NotNull(message = "Este campo no puede estar vacío")
    private int id;

    @Email(message = "El formato de correo no es válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
}