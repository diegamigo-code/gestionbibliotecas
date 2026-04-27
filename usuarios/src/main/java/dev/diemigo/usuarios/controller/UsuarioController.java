package dev.diemigo.usuarios.controller;


import dev.diemigo.usuarios.model.Usuario;
import dev.diemigo.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario nuevoUsuario(Usuario usuario){
        return usuarioService.nuevoUsuario(usuario);
    }

    @GetMapping("")
    public List<Usuario> mostrarUsuarios(){
        return usuarioService.mostrarUsuarios();
    }

    @GetMapping("{id}")
    public Usuario mostrarUsuario(Integer id){
        return usuarioService.mostrarUsuario(id);
    }

    @PutMapping
    public Usuario editarUsuario(Usuario usuario){
        return usuarioService.editarUsuario(usuario);
    }

    @DeleteMapping
    public void eliminarUsuario(Integer id){
        usuarioService.borrarUsuario(id);
    }
}
