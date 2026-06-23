package dev.diemigo.prestamos.controller;

import dev.diemigo.prestamos.dto.PrestamoDTO;
import dev.diemigo.prestamos.dto.PrestamoRespuestaDTO;
import dev.diemigo.prestamos.service.PrestamoService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PrestamoControllerTest {

    private final PrestamoService service = mock(PrestamoService.class);
    private final PrestamoController controller = new PrestamoController(service);

    @Test
    void listarDelegatesAlService() {
        when(service.listar()).thenReturn(List.of(PrestamoRespuestaDTO.builder().id(1L).build()));

        List<PrestamoRespuestaDTO> resultado = controller.listar();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }

    @Test
    void crearDelegatesAlService() {
        PrestamoDTO request = new PrestamoDTO("Ana", "Clean Code", "2026-06-22");
        when(service.crear(request)).thenReturn(PrestamoRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, controller.crear(request).getId());
    }

    @Test
    void obtenerDelegatesAlService() {
        when(service.buscarPorId(1L)).thenReturn(PrestamoRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, controller.obtener(1L).getId());
    }

    @Test
    void eliminarDelegatesAlService() {
        controller.eliminar(1L);

        verify(service).eliminar(1L);
    }
}
