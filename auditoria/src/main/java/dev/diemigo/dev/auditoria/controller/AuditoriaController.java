package dev.diemigo.dev.auditoria.controller;

import dev.diemigo.dev.auditoria.dto.AuditoriaDTO;
import dev.diemigo.dev.auditoria.service.AuditoriaService;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@Validated
public class AuditoriaController {

    private static final Logger log = LoggerFactory.getLogger(AuditoriaController.class);

    @Autowired
    private AuditoriaService auditoriaService;

    @PostMapping
    public ResponseEntity<AuditoriaDTO> registrar(@Valid @RequestBody AuditoriaDTO dto) {
        log.info("Iniciando registro de auditoría para el servicio: {}", dto.getServicioOrigen());
        AuditoriaDTO respuesta = auditoriaService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<AuditoriaDTO>> obtenerTodos() {
        return ResponseEntity.ok(auditoriaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(auditoriaService.obtenerPorId(id));
    }

    @GetMapping("/servicio/{servcioOrigen}")
    public ResponseEntity<AuditoriaDTO> obtenerPorServicioOrigen(@PathVariable String servcioOrigen) {
        log.info("Consultando a indice: obteniendo auditorias del servicio: {}", servcioOrigen);
        List <AuditoriaDTO> registros = auditoriaService.obtenerPorServicio(servcioOrigen);
        return ResponseEntity.ok((AuditoriaDTO) registros);
    }

    @GetMapping ("/resultado/{resultado}")
    public ResponseEntity<AuditoriaDTO> obtenerPorResultado(@PathVariable String resultado) {
        log.info("Consulta a indice: obteniendo auditorias del servicio: {}", resultado);
        List <AuditoriaDTO> registros = auditoriaService.obtenerPorResultado(resultado);
        return ResponseEntity.ok((AuditoriaDTO) registros);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        auditoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}