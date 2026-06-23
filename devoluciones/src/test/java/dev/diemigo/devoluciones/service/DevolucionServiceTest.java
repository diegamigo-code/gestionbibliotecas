package dev.diemigo.devoluciones.service;

import dev.diemigo.devoluciones.assembler.DevolucionModelAssembler;
import dev.diemigo.devoluciones.client.AuditoriaClient;
import dev.diemigo.devoluciones.dto.DevolucionDTO;
import dev.diemigo.devoluciones.dto.DevolucionRespuestaDTO;
import dev.diemigo.devoluciones.model.Devolucion;
import dev.diemigo.devoluciones.repository.DevolucionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DevolucionServiceTest {

    @Mock
    private DevolucionRepository repo;

    @Mock
    private DevolucionModelAssembler assembler;

    @Mock
    private AuditoriaClient auditoriaClient;

    @InjectMocks
    private DevolucionService service;

    @Test
    void crearPersisteDevolucionYNotificaAuditoria() {
        DevolucionDTO dto = new DevolucionDTO(10L, 20L);
        Devolucion guardada = new Devolucion(1L, 10L, 20L, LocalDate.now(), false);
        DevolucionRespuestaDTO respuesta = DevolucionRespuestaDTO.builder().id(1L).prestamoId(10L).build();

        when(repo.save(any(Devolucion.class))).thenReturn(guardada);
        when(assembler.toModel(guardada)).thenReturn(respuesta);

        DevolucionRespuestaDTO resultado = service.crear(dto);

        assertEquals(1L, resultado.getId());
        verify(repo).save(any(Devolucion.class));
        verify(auditoriaClient).registrarAccion(any());
    }

    @Test
    void listarRetornaDevolucionesMapeadas() {
        Devolucion devolucion = new Devolucion(1L, 10L, 20L, LocalDate.now(), false);
        when(repo.findAll()).thenReturn(List.of(devolucion));
        when(assembler.toModel(devolucion)).thenReturn(DevolucionRespuestaDTO.builder().id(1L).build());

        List<DevolucionRespuestaDTO> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }

    @Test
    void obtenerRetornaDevolucionExistente() {
        Devolucion devolucion = new Devolucion(1L, 10L, 20L, LocalDate.now(), false);
        when(repo.findById(1L)).thenReturn(Optional.of(devolucion));
        when(assembler.toModel(devolucion)).thenReturn(DevolucionRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, service.obtener(1L).getId());
    }

    @Test
    void obtenerLanzaErrorCuandoNoExiste() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.obtener(99L));
        verify(auditoriaClient).registrarAccion(any());
    }

    @Test
    void eliminarBorraCuandoExiste() {
        when(repo.existsById(1L)).thenReturn(true);

        service.eliminar(1L);

        verify(repo).deleteById(1L);
        verify(auditoriaClient).registrarAccion(any());
    }
}
