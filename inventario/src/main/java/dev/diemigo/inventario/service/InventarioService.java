package dev.diemigo.inventario.service;

import dev.diemigo.inventario.dto.InventarioDTO;
import dev.diemigo.inventario.exception.NotFoundException;
import dev.diemigo.inventario.model.Inventario;
import dev.diemigo.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    private InventarioDTO convertirADTO(Inventario inventario) {
        return new InventarioDTO(inventario.getTitulo(),inventario.getEstado(),inventario.getUbicacion());
    }

    public InventarioDTO crearInventario(InventarioDTO nuevoInventarioDTO) {
        Inventario inventario = new Inventario();
        inventario.setEstado(nuevoInventarioDTO.getEstado());
        inventario.setTitulo(nuevoInventarioDTO.getTitulo());
        inventario.setUbicacion(nuevoInventarioDTO.getUbicacion());

        Inventario guardado = inventarioRepository.save(inventario);
        return convertirADTO(guardado);
    }

    public InventarioDTO actualizarInventario(int id, InventarioDTO dto) {
        return inventarioRepository.findById(id)
                .map(inventario -> {
                    inventario.setTitulo(dto.getTitulo());
                    return convertirADTO(inventarioRepository.save(inventario));
                })
                .orElseThrow(() -> new RuntimeException("Objeto con ID " + id + " no encontrado"));
    }

    public List<InventarioDTO> listarUsuarios() {
        return inventarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public InventarioDTO buscarPorId(int id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el objeto con ID: " + id));
        return convertirADTO(inventario);
    }

    public void eliminarInventario(int id) {
        if (!inventarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El objeto no existe");
        }
        inventarioRepository.deleteById(id);
    }
}
