package data.notificaciones.controller;

import data.notificaciones.service.NotificacionService;
import data.notificaciones.model.DTO.DTONotificaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class notificacionController {
    @Autowired
    private NotificacionService NotificacionService;

    @GetMapping
    public List<DTONotificaciones> findAll() {
        return NotificacionService.obtenerNotificaciones();

    }

    @GetMapping("/{id}")
    public DTONotificaciones findById(@PathVariable int id) {

        return NotificacionService.getNotificacionesById(id);
    }



    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        NotificacionService.deleteNotificaciones(id);
    }

}
