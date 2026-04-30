package dev.diemigo.dev.prestamo.controller;

import dev.diemigo.dev.prestamo.model.RegistroAuditoria;
import dev.diemigo.dev.prestamo.service.AuditoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @PostMapping
    public ResponseEntity<RegistroAuditoria> registrar(@Valid @RequestBody RegistroAuditoria registro) {
        RegistroAuditoria nuevo = auditoriaService.registrar(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<RegistroAuditoria>> obtenerTodos() {
        return ResponseEntity.ok(auditoriaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroAuditoria> obtenerPorId(@PathVariable Long id) {
        Optional<RegistroAuditoria> registro = auditoriaService.obtenerPorId(id);
        return registro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/servicio/{servicioOrigen}")
    public ResponseEntity<List<RegistroAuditoria>> obtenerPorServicio(
            @PathVariable String servicioOrigen) {
        return ResponseEntity.ok(auditoriaService.obtenerPorServicio(servicioOrigen));
    }

    @GetMapping("/resultado/{resultado}")
    public ResponseEntity<List<RegistroAuditoria>> obtenerPorResultado(
            @PathVariable String resultado) {
        return ResponseEntity.ok(auditoriaService.obtenerPorResultado(resultado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        auditoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}