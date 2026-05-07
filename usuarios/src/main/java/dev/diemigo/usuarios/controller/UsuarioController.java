package dev.diemigo.usuarios.controller;


import dev.diemigo.usuarios.dto.UsuarioRespuestaDTO;
import dev.diemigo.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            List<UsuarioRespuestaDTO> usuarios = usuarioService.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioRespuestaDTO nuevoUsuario) {
        try{
            UsuarioRespuestaDTO usuario = usuarioService.crearUsuario(nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error al intentar crear el usuario");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id){
        try{
            return ResponseEntity.ok(usuarioService.buscarPorId(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioRespuestaDTO usuario){
        try{
            return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id){
        try{
            usuarioService.eliminarUsuario(id);
            return null;
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

}
