package dev.diemigo.gestionbibliotecas.repository;

import dev.diemigo.gestionbibliotecas.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}
