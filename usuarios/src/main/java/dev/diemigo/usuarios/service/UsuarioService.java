package dev.diemigo.usuarios.service;

import dev.diemigo.usuarios.model.Usuario;
import dev.diemigo.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario nuevoUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> mostrarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario mostrarUsuario(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario editarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void borrarUsuario(Integer id){
        usuarioRepository.deleteById(id);
        System.out.println("Usuario eliminado con éxito");
    }

}
