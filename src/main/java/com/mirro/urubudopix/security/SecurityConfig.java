package com.mirro.urubudopix.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita a autenticação para todas as requisições
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll() // Permite todas as requisições
                // sem autenticação
                )
                // Desabilita a tela de login
                .formLogin(login -> login.disable())
                // Desabilita a proteção contra CSRF, já que geralmente APIs REST não usam isso
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
