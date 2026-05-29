package dev.diegoamigo.devoluciones.service;

import dev.diegoamigo.devoluciones.dto.DevolucionDTO;
import dev.diegoamigo.devoluciones.dto.DevolucionRespuestaDTO;
import dev.diegoamigo.devoluciones.model.Devolucion;
import dev.diegoamigo.devoluciones.repository.DevolucionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository repo;

    private DevolucionRespuestaDTO convertirADTO(Devolucion d) {
        return new DevolucionRespuestaDTO(
                d.getId(),
                d.getPrestamoId(),
                d.getUsuarioId(),
                d.getFechaDevolucionReal(),
                d.isAtraso()
        );
    }

    public DevolucionRespuestaDTO crear(DevolucionDTO dto) {
        Devolucion d = new Devolucion();
        d.setPrestamoId(dto.getPrestamoId());
        d.setUsuarioId(dto.getUsuarioId());
        d.setFechaDevolucionReal(LocalDate.now());
        d.setAtraso(false);
        return convertirADTO(repo.save(d));
    }

    public List<DevolucionRespuestaDTO> listar() {
        return repo.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public DevolucionRespuestaDTO obtener(Long id) {
        Devolucion d = repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No existe devolución con ID: " + id));
        return convertirADTO(d);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("No existe devolución con ID: " + id);
        }
        repo.deleteById(id);
    }
}