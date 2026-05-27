package dev.diemigo.usuarios.service;

import dev.diemigo.usuarios.dto.UsuarioRespuestaDTO;
import dev.diemigo.usuarios.exception.NotFoundException;
import dev.diemigo.usuarios.model.Usuario;
import dev.diemigo.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioRespuestaDTO convertirADTO(Usuario usuario) {
        return new UsuarioRespuestaDTO(usuario.getId(), usuario.getCorreo());
    }

    public UsuarioRespuestaDTO crearUsuario(UsuarioRespuestaDTO nuevoUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(nuevoUsuarioDTO.getCorreo());
        usuario.setContrasenia("password123");
        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);
        return convertirADTO(guardado);
    }

    public UsuarioRespuestaDTO actualizarUsuario(int id, UsuarioRespuestaDTO dto) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setCorreo(dto.getCorreo());
                    return convertirADTO(usuarioRepository.save(usuario));
                })
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
    }

    public List<UsuarioRespuestaDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public UsuarioRespuestaDTO buscarPorId(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el usuario con ID: " + id));
        return convertirADTO(usuario);
    }

    public void eliminarUsuario(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }
    public void nada(){}
}