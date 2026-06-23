package dev.diemigo.prestamos.service;

import dev.diemigo.prestamos.assembler.PrestamoModelAssembler;
import dev.diemigo.prestamos.client.AuditoriaClient;
import dev.diemigo.prestamos.dto.PrestamoDTO;
import dev.diemigo.prestamos.dto.PrestamoRespuestaDTO;
import dev.diemigo.prestamos.model.Prestamo;
import dev.diemigo.prestamos.repository.PrestamoRepository;
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
class PrestamoServiceTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @Mock
    private PrestamoModelAssembler assembler;

    @Mock
    private AuditoriaClient auditoriaClient;

    @InjectMocks
    private PrestamoService service;

    @Test
    void crearPersistePrestamoYNotificaAuditoria() {
        PrestamoDTO dto = new PrestamoDTO("Ana", "Clean Code", "2026-06-22");
        Prestamo guardado = new Prestamo(1L, "Ana", "Clean Code", "2026-06-22");
        PrestamoRespuestaDTO respuesta = PrestamoRespuestaDTO.builder().id(1L).nombreCliente("Ana").build();

        when(prestamoRepository.save(any(Prestamo.class))).thenReturn(guardado);
        when(assembler.toModel(guardado)).thenReturn(respuesta);

        PrestamoRespuestaDTO resultado = service.crear(dto);

        assertEquals(1L, resultado.getId());
        verify(prestamoRepository).save(any(Prestamo.class));
        verify(auditoriaClient).registrarAccion(any());
    }

    @Test
    void listarRetornaPrestamosMapeados() {
        Prestamo prestamo = new Prestamo(1L, "Ana", "Clean Code", "2026-06-22");
        when(prestamoRepository.findAll()).thenReturn(List.of(prestamo));
        when(assembler.toModel(prestamo)).thenReturn(PrestamoRespuestaDTO.builder().id(1L).build());

        List<PrestamoRespuestaDTO> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }

    @Test
    void buscarPorIdRetornaPrestamoExistente() {
        Prestamo prestamo = new Prestamo(1L, "Ana", "Clean Code", "2026-06-22");
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));
        when(assembler.toModel(prestamo)).thenReturn(PrestamoRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, service.buscarPorId(1L).getId());
    }

    @Test
    void buscarPorIdLanzaErrorCuandoNoExiste() {
        when(prestamoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(99L));
        verify(auditoriaClient).registrarAccion(any());
    }

    @Test
    void eliminarBorraCuandoExiste() {
        when(prestamoRepository.existsById(1L)).thenReturn(true);

        service.eliminar(1L);

        verify(prestamoRepository).deleteById(1L);
        verify(auditoriaClient).registrarAccion(any());
    }
}
