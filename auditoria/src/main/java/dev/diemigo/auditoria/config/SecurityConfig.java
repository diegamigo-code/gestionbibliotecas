package dev.diemigo.auditoria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles(Rol.ADMIN.name())
                .build();

        UserDetails bibliotecario = User.withUsername("bibliotecario")
                .password("{noop}biblio123")
                .roles(Rol.BIBLIOTECARIO.name())
                .build();

        UserDetails usuario = User.withUsername("usuario")
                .password("{noop}usuario123")
                .roles(Rol.USUARIO.name())
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                bibliotecario,
                usuario
        );
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/actuator/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/api/auditoria/**")
                        .hasRole(Rol.ADMIN.name())

                        .requestMatchers(HttpMethod.POST, "/api/auditoria/**")
                        .hasAnyRole(
                                Rol.ADMIN.name(),
                                Rol.BIBLIOTECARIO.name()
                        )

                        .requestMatchers(HttpMethod.GET, "/api/auditoria/**")
                        .hasAnyRole(
                                Rol.ADMIN.name(),
                                Rol.BIBLIOTECARIO.name()
                        )

                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}