package dev.diemigo.gestionbibliotecas.repository;
import dev.diemigo.gestionbibliotecas.models.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
