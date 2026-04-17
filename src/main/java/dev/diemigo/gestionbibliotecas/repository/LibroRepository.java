package dev.diemigo.gestionbibliotecas.repository;

import dev.diemigo.gestionbibliotecas.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
