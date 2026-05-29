package dev.diegoamigo.prestamos.controller;

import dev.diegoamigo.prestamos.dto.PrestamoDTO;
import dev.diegoamigo.prestamos.dto.PrestamoRespuestaDTO;
import dev.diegoamigo.prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrestamoRespuestaDTO crear(@Valid @RequestBody PrestamoDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<PrestamoRespuestaDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PrestamoRespuestaDTO obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}