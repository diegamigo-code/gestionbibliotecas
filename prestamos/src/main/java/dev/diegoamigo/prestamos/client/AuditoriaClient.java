package dev.diegoamigo.prestamos.client;

import dev.diegoamigo.prestamos.config.FeignConfig;
import dev.diegoamigo.prestamos.dto.AuditoriaClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auditoria", path = "/api/auditoria", configuration = FeignConfig.class)
public interface AuditoriaClient {

    /**
     * Este método define el envío de datos hacia el controlador de Auditoría.
     * Al llamarlo desde el Service, OpenFeign se encarga de transformarlo en un POST real.
     */
    @PostMapping
    void registrarAccion(@RequestBody AuditoriaClientDTO dto);
}