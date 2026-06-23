package dev.diemigo.multas.service;

import dev.diemigo.multas.assembler.MultaAssembler;
import dev.diemigo.multas.client.AuditoriaClient;
import dev.diemigo.multas.dto.MultaDTO;
import dev.diemigo.multas.dto.MultaRespuestaDTO;
import dev.diemigo.multas.model.Multa;
import dev.diemigo.multas.repository.MultaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultaServiceTest {

    @Mock
    private MultaRepository repository;

    @Mock
    private MultaAssembler assembler;

    @Mock
    private AuditoriaClient auditoriaClient;

    @InjectMocks
    private MultaService service;

    @Test
    void guardarPersisteMultaYNotificaAuditoria() {
        MultaDTO dto = new MultaDTO(10L, 2500, "Atraso", false);
        Multa guardada = new Multa(1L, 10L, 2500, "Atraso", false);
        MultaRespuestaDTO respuesta = MultaRespuestaDTO.builder().id(1L).prestamoId(10L).build();

        when(repository.save(any(Multa.class))).thenReturn(guardada);
        when(assembler.toModel(guardada)).thenReturn(respuesta);

        MultaRespuestaDTO resultado = service.guardar(dto);

        assertEquals(1L, resultado.getId());
        verify(repository).save(any(Multa.class));
        verify(auditoriaClient).registrarAccion(any());
    }

    @Test
    void listarRetornaMultasMapeadas() {
        Multa multa = new Multa(1L, 10L, 2500, "Atraso", false);
        when(repository.findAll()).thenReturn(List.of(multa));
        when(assembler.toModel(multa)).thenReturn(MultaRespuestaDTO.builder().id(1L).build());

        List<MultaRespuestaDTO> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }

    @Test
    void obtenerRetornaMultaExistente() {
        Multa multa = new Multa(1L, 10L, 2500, "Atraso", false);
        when(repository.findById(1L)).thenReturn(Optional.of(multa));
        when(assembler.toModel(multa)).thenReturn(MultaRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, service.obtener(1L).getId());
    }

    @Test
    void obtenerLanzaErrorCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.obtener(99L));
        verify(auditoriaClient).registrarAccion(any());
    }

    @Test
    void eliminarBorraCuandoExiste() {
        when(repository.existsById(1L)).thenReturn(true);

        service.eliminar(1L);

        verify(repository).deleteById(1L);
        verify(auditoriaClient).registrarAccion(any());
    }
}
