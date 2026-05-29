package dev.diegoamigo.devoluciones.controller;

import dev.diegoamigo.devoluciones.dto.DevolucionDTO;
import dev.diegoamigo.devoluciones.dto.DevolucionRespuestaDTO;
import dev.diegoamigo.devoluciones.service.DevolucionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DevolucionRespuestaDTO crear(@Valid @RequestBody DevolucionDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<DevolucionRespuestaDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public DevolucionRespuestaDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}