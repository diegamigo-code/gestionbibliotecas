package dev.diemigo.dev.prestamo.repository;

import dev.diemigo.dev.prestamo.model.RegistroAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroAuditoriaRepository extends JpaRepository<RegistroAuditoria, Long> {


    List<RegistroAuditoria> findByServicioOrigen(String servicioOrigen);

    List<RegistroAuditoria> findByResultado(String resultado);
}
