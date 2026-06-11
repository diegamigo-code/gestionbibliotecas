package data.notificaciones.service;

import data.notificaciones.exception.NotFoundException;
import data.notificaciones.model.Notificacion;
import data.notificaciones.repository.NotificacionRepository;
import data.notificaciones.model.DTO.DTONotificaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository repositoryNotificacion;

    private DTONotificaciones convertirDTO(Notificacion notificacion) {
     return new DTONotificaciones(notificacion.getDia_entrega(),notificacion.getNotificacion_titulo(),notificacion.getDescripcion());
    }

    public List<DTONotificaciones> obtenerNotificaciones() {
        return repositoryNotificacion.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }


    public DTONotificaciones getNotificacionesById (int id){
        Notificacion notificacion = repositoryNotificacion.findById(id).orElseThrow(); new NotFoundException("");
        return convertirDTO(notificacion);
    }


    public DTONotificaciones addNotificacion(Notificacion modelNotificacion){
        repositoryNotificacion.save(modelNotificacion);
        return modelNotificacion;
    }
    public void deleteNotificaciones(int id){repositoryNotificacion.deleteById(id);
    }

    
}
