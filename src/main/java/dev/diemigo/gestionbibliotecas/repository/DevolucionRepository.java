package dev.diemigo.gestionbibliotecas.repository;

import dev.diemigo.gestionbibliotecas.models.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {
}
