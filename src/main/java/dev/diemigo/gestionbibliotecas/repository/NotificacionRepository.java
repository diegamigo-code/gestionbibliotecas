package dev.diemigo.gestionbibliotecas.repository;

import dev.diemigo.gestionbibliotecas.models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
}
