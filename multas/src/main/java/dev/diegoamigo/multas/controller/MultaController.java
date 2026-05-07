package dev.diegoamigo.multas.controller;

import dev.diegoamigo.multas.dto.MultaDTO;
import dev.diegoamigo.multas.model.Multa;
import dev.diegoamigo.multas.service.MultaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/multas")
public class MultaController {

    private final MultaService service;

    public MultaController(MultaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Multa> listar() {
        return service.listar();
    }

    @PostMapping
    public Multa guardar(@RequestBody MultaDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping("/{id}") 
    public Multa obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}