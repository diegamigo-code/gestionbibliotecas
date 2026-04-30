package dev.diemigo.dev.prestamo.service;

import dev.diemigo.dev.prestamo.model.RegistroAuditoria;
import dev.diemigo.dev.prestamo.repository.RegistroAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaService {

    @Autowired
    private RegistroAuditoriaRepository auditoriaRepository;

    public RegistroAuditoria registrar(RegistroAuditoria registro) {
        registro.setFechaHora(LocalDateTime.now()); // siempre se genera aquí
        return auditoriaRepository.save(registro);
    }


    public List<RegistroAuditoria> obtenerTodos() {
        return auditoriaRepository.findAll();
    }

    public Optional<RegistroAuditoria> obtenerPorId(Long id) {
        return auditoriaRepository.findById(id);
    }


    public List<RegistroAuditoria> obtenerPorServicio(String servicioOrigen) {
        return auditoriaRepository.findByServicioOrigen(servicioOrigen);
    }


    public List<RegistroAuditoria> obtenerPorResultado(String resultado) {
        return auditoriaRepository.findByResultado(resultado);
    }


    public void eliminar(Long id) {
        auditoriaRepository.deleteById(id);
    }
}
