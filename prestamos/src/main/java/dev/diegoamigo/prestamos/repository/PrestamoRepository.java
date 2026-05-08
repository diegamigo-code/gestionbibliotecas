package dev.diegoamigo.prestamos.repository;

import dev.diegoamigo.prestamos.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}