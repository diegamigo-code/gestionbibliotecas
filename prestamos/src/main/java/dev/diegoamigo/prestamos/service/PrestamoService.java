package dev.diegoamigo.prestamos.service;

import dev.diegoamigo.prestamos.dto.PrestamoDTO;
import dev.diegoamigo.prestamos.dto.PrestamoRespuestaDTO;
import dev.diegoamigo.prestamos.model.Prestamo;
import dev.diegoamigo.prestamos.repository.PrestamoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    private PrestamoRespuestaDTO convertirADTO(Prestamo prestamo) {
        return new PrestamoRespuestaDTO(
                prestamo.getId(),
                prestamo.getNombreCliente(),
                prestamo.getLibro(),
                prestamo.getFechaPrestamo()
        );
    }

    public PrestamoRespuestaDTO crear(PrestamoDTO dto) {
        Prestamo prestamo = new Prestamo();
        prestamo.setNombreCliente(dto.getNombreCliente());
        prestamo.setLibro(dto.getLibro());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        return convertirADTO(prestamoRepository.save(prestamo));
    }

    public List<PrestamoRespuestaDTO> listar() {
        return prestamoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PrestamoRespuestaDTO buscarPorId(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No existe préstamo con ID: " + id));
        return convertirADTO(prestamo);
    }

    public void eliminar(Long id) {
        if (!prestamoRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe préstamo con ID: " + id);
        }
        prestamoRepository.deleteById(id);
    }
}