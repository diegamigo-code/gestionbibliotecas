package dev.diegoamigo.multas.service;

import dev.diegoamigo.multas.dto.MultaDTO;
import dev.diegoamigo.multas.dto.MultaRespuestaDTO;
import dev.diegoamigo.multas.model.Multa;
import dev.diegoamigo.multas.repository.MultaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MultaService {

    private final MultaRepository repository;

    public MultaService(MultaRepository repository) {
        this.repository = repository;
    }

    private MultaRespuestaDTO convertirADTO(Multa m) {
        return new MultaRespuestaDTO(
                m.getId(),
                m.getPrestamoId(),
                m.getMonto(),
                m.getMotivo(),
                m.isPagada()
        );
    }

    public List<MultaRespuestaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MultaRespuestaDTO guardar(MultaDTO dto) {
        Multa multa = new Multa();
        multa.setPrestamoId(dto.getPrestamoId());
        multa.setMonto(dto.getMonto());
        multa.setMotivo(dto.getMotivo());
        multa.setPagada(dto.isPagada());
        return convertirADTO(repository.save(multa));
    }

    public MultaRespuestaDTO obtener(Long id) {
        Multa multa = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No existe multa con ID: " + id));
        return convertirADTO(multa);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("No existe multa con ID: " + id);
        }
        repository.deleteById(id);
    }
}