package dev.diemigo.inventario.controller;

import dev.diemigo.inventario.dto.InventarioDTO;
import dev.diemigo.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;
    
    @GetMapping
    public ResponseEntity<List<InventarioDTO>> getInventarios() {
        List<InventarioDTO> inventario = inventarioService.listarUsuarios();
        return ResponseEntity.ok(inventario);
    }

    @PostMapping
    public ResponseEntity<?> crearInventario(@Valid @RequestBody InventarioDTO nuevoInventario) {
        try {
            InventarioDTO inventario = inventarioService.crearInventario(nuevoInventario);
            return ResponseEntity.status(HttpStatus.CREATED).body(inventario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el objeto: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventarioById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(inventarioService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objeto no encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInventario(@PathVariable int id, @RequestBody InventarioDTO inventario) {
        try {
            return ResponseEntity.ok(inventarioService.actualizarInventario(id, inventario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar: Objeto no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInventario(@PathVariable int id) {
        try {
            inventarioService.eliminarInventario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar: Objeto no encontrado");
        }
    }
}
