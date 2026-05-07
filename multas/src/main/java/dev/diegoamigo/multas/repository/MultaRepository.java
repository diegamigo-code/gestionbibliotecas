package dev.diegoamigo.multas.repository;

import dev.diegoamigo.multas.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultaRepository extends JpaRepository<Multa, Long> {
}