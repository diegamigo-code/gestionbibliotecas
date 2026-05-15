package dev.diemigo.usuarios.controller;


import dev.diemigo.usuarios.dto.AuthLoginRequestDTO;
import dev.diemigo.usuarios.dto.AuthResponseDTO;
import dev.diemigo.usuarios.model.Usuario;
import dev.diemigo.usuarios.repository.UsuarioRepository;
import dev.diemigo.usuarios.security.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody AuthLoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasenia())
        );
        Usuario usuario = userRepository.findByCorreo(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));
        return new AuthResponseDTO(
                jwtService.generateToken(usuario),
                jwtService.getExpirationMs(),
                usuario.getCorreo(),
                usuario.getRol()
        );
    }
}