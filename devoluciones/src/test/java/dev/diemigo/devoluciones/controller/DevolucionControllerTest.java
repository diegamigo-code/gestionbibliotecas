package dev.diemigo.devoluciones.controller;

import dev.diemigo.devoluciones.dto.DevolucionDTO;
import dev.diemigo.devoluciones.dto.DevolucionRespuestaDTO;
import dev.diemigo.devoluciones.service.DevolucionService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DevolucionControllerTest {

    private final DevolucionService service = mock(DevolucionService.class);
    private final DevolucionController controller = new DevolucionController(service);

    @Test
    void listarDelegatesAlService() {
        when(service.listar()).thenReturn(List.of(DevolucionRespuestaDTO.builder().id(1L).build()));

        List<DevolucionRespuestaDTO> resultado = controller.listar();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }

    @Test
    void crearDelegatesAlService() {
        DevolucionDTO request = new DevolucionDTO(10L, 20L);
        when(service.crear(request)).thenReturn(DevolucionRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, controller.crear(request).getId());
    }

    @Test
    void obtenerDelegatesAlService() {
        when(service.obtener(1L)).thenReturn(DevolucionRespuestaDTO.builder().id(1L).build());

        assertEquals(1L, controller.obtener(1L).getId());
    }

    @Test
    void eliminarDelegatesAlService() {
        controller.eliminar(1L);

        verify(service).eliminar(1L);
    }
}
