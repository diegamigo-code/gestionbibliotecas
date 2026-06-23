package dev.diemigo.usuarios.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioRespuestaDTO extends RepresentationModel<UsuarioRespuestaDTO> {

    @NotNull(message = "Este campo no puede estar vacío")
    private Long id;

    @Email(message = "El formato de correo no es válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
}