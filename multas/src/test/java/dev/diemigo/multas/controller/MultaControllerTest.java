package dev.diemigo.multas.controller;

import dev.diemigo.multas.dto.MultaDTO;
import dev.diemigo.multas.dto.MultaRespuestaDTO;
import dev.diemigo.multas.service.MultaService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MultaControllerTest {

    private final MultaService service = mock(MultaService.class);
    private final MultaController controller = new MultaController(service);

    @Test
    void listarDelegatesAlService() {
        when(service.listar()).thenReturn(List.of(MultaRespuestaDTO.builder().id(1L).build()));

        List<MultaRespuestaDTO> resultado = controller.listar();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }

    @Test
    void guardarDelegatesAlService() {
        MultaDTO request = new MultaDTO(10L, 2500, "Atraso", false);
        when(service.guardar(request)).thenReturn(MultaRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, controller.guardar(request).getId());
    }

    @Test
    void obtenerDelegatesAlService() {
        when(service.obtener(1L)).thenReturn(MultaRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, controller.obtener(1L).getId());
    }

    @Test
    void eliminarDelegatesAlService() {
        controller.eliminar(1L);

        verify(service).eliminar(1L);
    }
}
