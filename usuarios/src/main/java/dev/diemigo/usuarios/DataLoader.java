package dev.diemigo.usuarios;

import dev.diemigo.usuarios.model.*;
import dev.diemigo.usuarios.repository.*;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepository.count() > 0) {
            log.info("La base de datos de usuarios ya contiene registros. Saltando el DataLoader.");
            return;
        }

        log.info("Cargando datos de prueba (Profile: dev)...");
        Faker faker = new Faker();
        Random random = new Random();

        // Generar usuarios
        for (long i = 0; i < 50; i++) {
            Usuario usuario = new Usuario();
            usuario.setId(i + 1);
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContrasenia(faker.lorem().characters(10, 16));
            usuario.setActivo(random.nextBoolean());
            usuario.setRol(faker.options().option(UsuarioRol.class));
            usuarioRepository.save(usuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();
        log.info("¡Carga masiva finalizada exitosamente! Se han insertado {} usuarios ficticios.", usuarioRepository.count());

    }
}