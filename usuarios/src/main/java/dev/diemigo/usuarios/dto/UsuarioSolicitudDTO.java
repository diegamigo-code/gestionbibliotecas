package dev.diemigo.usuarios.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSolicitudDTO {
    @Email(message = "Correo inválido")
    @NotBlank(message = "No puede estar vacío")
    private String correo;
}
