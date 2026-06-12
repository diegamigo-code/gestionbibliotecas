package dev.diemigo.auditoria.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import dev.diemigo.auditoria.dto.AuditoriaDTO;
import dev.diemigo.auditoria.service.AuditoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@Tag(
        name = "Gestión de Auditoría",
        description = "Endpoints para el registro, consulta y administración de la trazabilidad del sistema"
)
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @PostMapping
    @Operation(
            summary = "Registrar un log de auditoría",
            description = "Permite persistir un nuevo registro de trazabilidad en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    public ResponseEntity<AuditoriaDTO> registrar(@Valid @RequestBody AuditoriaDTO dto) {

        AuditoriaDTO respuesta = auditoriaService.registrar(dto);

        respuesta.add(
                linkTo(methodOn(AuditoriaController.class)
                        .obtenerPorId(respuesta.getId()))
                        .withSelfRel()
        );

        respuesta.add(
                linkTo(methodOn(AuditoriaController.class)
                        .obtenerTodos())
                        .withRel("lista-completa")
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    @Operation(
            summary = "Obtener historial de auditoría",
            description = "Recupera todos los registros históricos de auditoría almacenados en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    public ResponseEntity<List<AuditoriaDTO>> obtenerTodos() {

        List<AuditoriaDTO> lista = auditoriaService.obtenerTodos();

        lista.forEach(dto ->
                dto.add(
                        linkTo(methodOn(AuditoriaController.class)
                                .obtenerPorId(dto.getId()))
                                .withSelfRel()
                )
        );

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consultar auditoría por ID",
            description = "Obtiene un registro específico de auditoría utilizando su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    public ResponseEntity<AuditoriaDTO> obtenerPorId(@PathVariable Long id) {

        AuditoriaDTO dto = auditoriaService.obtenerPorId(id);

        dto.add(
                linkTo(methodOn(AuditoriaController.class)
                        .obtenerPorId(id))
                        .withSelfRel()
        );

        dto.add(
                linkTo(methodOn(AuditoriaController.class)
                        .obtenerTodos())
                        .withRel("lista-completa")
        );

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar registro de auditoría",
            description = "Elimina un registro de auditoría existente mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro eliminado correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        auditoriaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}