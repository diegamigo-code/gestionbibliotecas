package dev.diegoamigo.devoluciones.repository;

import dev.diegoamigo.devoluciones.model.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {
}