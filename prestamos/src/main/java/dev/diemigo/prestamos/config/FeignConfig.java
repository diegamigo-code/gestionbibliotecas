package dev.diemigo.prestamos.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     * Interceptor global para que OpenFeign envíe automáticamente la cabecera
     * de autenticación HTTP Basic requerida por el microservicio seguro de Auditoría.
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        // Credenciales autorizadas (BIBLIOTECARIO) configuradas en el servicio de auditoría
        return new BasicAuthRequestInterceptor("bibliotecario", "biblio123");
    }
}