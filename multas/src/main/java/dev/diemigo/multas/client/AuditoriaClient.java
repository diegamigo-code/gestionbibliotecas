package dev.diemigo.multas.client;

import dev.diemigo.multas.config.FeignConfig;
import dev.diemigo.multas.dto.AuditoriaClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Se asume el nombre lógico registrado en tu Servidor de Descubrimiento (Eureka / Consul)
// Vinculamos la configuración de seguridad HTTP Basic específica definida anteriormente
@FeignClient(name = "auditoria", path = "/api/auditoria", configuration = FeignConfig.class)
public interface AuditoriaClient {

    @PostMapping
    void registrarAccion(@RequestBody AuditoriaClientDTO dto);
}
