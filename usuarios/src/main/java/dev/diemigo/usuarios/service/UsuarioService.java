package dev.diemigo.usuarios.service;

import dev.diemigo.usuarios.dto.UsuarioRespuestaDTO;
import dev.diemigo.usuarios.exception.NotFoundException;
import dev.diemigo.usuarios.model.Usuario;
import dev.diemigo.usuarios.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);


    private UsuarioRespuestaDTO convertirADTO(Usuario usuario) {
        return new UsuarioRespuestaDTO(usuario.getId(), usuario.getCorreo());
    }

    @Transactional
    public UsuarioRespuestaDTO crearUsuario(UsuarioRespuestaDTO nuevoUsuarioDTO) {

        log.debug("Creando usuario en la base de datos: {}", nuevoUsuarioDTO);

        Usuario usuario = new Usuario();
        usuario.setCorreo(nuevoUsuarioDTO.getCorreo());
        usuario.setContrasenia("password123");
        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);

        log.info("Usuario guardado en la base de datos: {}", guardado);
        return convertirADTO(guardado);
    }

    @Transactional
    public UsuarioRespuestaDTO actualizarUsuario(Long id, UsuarioRespuestaDTO dto) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setCorreo(dto.getCorreo());
                    return convertirADTO(usuarioRepository.save(usuario));
                })
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<UsuarioRespuestaDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioRespuestaDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el usuario con ID: " + id));
        return convertirADTO(usuario);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }
}