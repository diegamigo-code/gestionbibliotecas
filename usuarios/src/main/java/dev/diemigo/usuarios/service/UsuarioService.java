package dev.diemigo.usuarios.service;


import dev.diemigo.usuarios.dto.UsuarioSolicitudDTO;
import dev.diemigo.usuarios.model.Usuario;
import dev.diemigo.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import dev.diemigo.usuarios.dto.UsuarioRespuestaDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    private UsuarioRespuestaDTO convertir(Usuario usuario) {
        return new UsuarioRespuestaDTO(usuario.getId(), usuario.getCorreo());
    }

    public UsuarioRespuestaDTO crearUsuario(UsuarioRespuestaDTO nuevoUsuario){
        Usuario usuario = new Usuario(0,nuevoUsuario.getCorreo(),"password",true);
        usuario = usuarioRepository.save(usuario);
        return new UsuarioRespuestaDTO(usuario.getId(), usuario.getCorreo());
    }

    public UsuarioRespuestaDTO actualizarUsuario(int id,UsuarioRespuestaDTO usuarioActualizado){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuario = usuarioRepository.save(usuario);
        return new UsuarioRespuestaDTO(usuario.getId(), usuario.getCorreo());
    }

    public List<UsuarioRespuestaDTO> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioRespuestaDTO> listaUsuarios = new ArrayList<>();
        for(Usuario usuario : usuarios){
            listaUsuarios.add(convertir(usuario));
        }
        return listaUsuarios;
    }

    public UsuarioSolicitudDTO buscarPorId(int id){
        Usuario usuario =  usuarioRepository.findById(id).get();
        return new UsuarioSolicitudDTO(usuario.getCorreo());
    }

    public void eliminarUsuario(int id){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.deleteById(id);
    }

}
