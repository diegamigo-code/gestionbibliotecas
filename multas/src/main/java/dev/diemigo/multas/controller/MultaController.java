package dev.diemigo.multas.controller;

import dev.diemigo.multas.dto.MultaDTO;
import dev.diemigo.multas.dto.MultaRespuestaDTO;
import dev.diemigo.multas.service.MultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
public class MultaController {

    private final MultaService service;

    public MultaController(MultaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MultaRespuestaDTO> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MultaRespuestaDTO guardar(@Valid @RequestBody MultaDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping("/{id}")
    public MultaRespuestaDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
