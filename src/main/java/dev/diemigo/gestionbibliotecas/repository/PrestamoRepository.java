package dev.diemigo.gestionbibliotecas.repository;
import dev.diemigo.gestionbibliotecas.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
