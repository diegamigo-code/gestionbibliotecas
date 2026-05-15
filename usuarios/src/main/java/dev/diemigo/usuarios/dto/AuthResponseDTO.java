package dev.diemigo.usuarios.dto;


import dev.diemigo.usuarios.model.UsuarioRol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String tokenType = "Bearer";
    private String accessToken;
    private long expiresIn;
    private String email;
    private UsuarioRol rol;

    public AuthResponseDTO(String accessToken, long expiresIn, String email, UsuarioRol rol) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.email = email;
        this.rol = rol;
    }
}