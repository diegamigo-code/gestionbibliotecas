package dev.diemigo.usuarios.controller;


import dev.diemigo.usuarios.assembler.UsuarioModelAssembler;
import dev.diemigo.usuarios.dto.UsuarioRespuestaDTO;
import dev.diemigo.usuarios.exception.RequestException;
import dev.diemigo.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gestión de usuarios", description = "Endpoints para el manejo de datos de usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;


    @GetMapping
    @Operation(summary = "Obtener listado completo de usuarios")
    @ApiResponse(responseCode = "200", description = "Mostrando listado")
    public ResponseEntity<List<UsuarioRespuestaDTO>> getUsuarios() {

        List<UsuarioRespuestaDTO> usuarios = usuarioService.listarUsuarios()
                .stream()
                .map(usuarioModelAssembler::toModel)
                .collect(Collectors.toList());
        if(usuarios.isEmpty()){
            throw new RequestException("Usuarios no encontrados");
        }
        return ResponseEntity.ok(usuarios);
    }



    @PostMapping
    @Operation(summary ="Crea un nuevo usuario" , description = "Permite registrar un usuario que es persistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registrado con éxito",
                    content = @Content(schema = @Schema(implementation = UsuarioRespuestaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Estructura inválida", content = @Content)
    })
    public ResponseEntity<?> createUsuario(@Valid @RequestBody UsuarioRespuestaDTO nuevoUsuario) {

        if(nuevoUsuario.getCorreo() ==  null || nuevoUsuario.getCorreo().isEmpty()) {
            throw new RequestException("El correo es requerido");
        }
        UsuarioRespuestaDTO respuesta = usuarioService.crearUsuario(nuevoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioModelAssembler.toModel(respuesta));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta los datos de un usuario al buscar su ID única"
            ,description = "Busca coincidencias en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o no existe")
    })
    public ResponseEntity<UsuarioRespuestaDTO> getUsuarioById(
        @Parameter(description = "ID del usuario", required = true, example = "1")
        @PathVariable Long id){

        UsuarioRespuestaDTO usuarioBuscado = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(usuarioModelAssembler.toModel(usuarioBuscado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un usuario por su ID",
            description = "Permite modificar los datos de un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UsuarioRespuestaDTO> updateUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRespuestaDTO usuario) {

        UsuarioRespuestaDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);

        return ResponseEntity.ok(usuarioModelAssembler.toModel(usuarioActualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un Usuario por su ID", description = "Elimina un Usuario de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar: No existe usuario con esa ID")
    })
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        var buscado = usuarioService.buscarPorId(id);
        if(buscado == null){
            throw new RequestException("Usuario no encontrado");
        }
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}