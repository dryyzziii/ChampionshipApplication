package com.football.championship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Autoriser toutes les origines pendant le développement
        config.addAllowedOrigin("*");
        
        // Vous pouvez aussi spécifier uniquement l'origine de votre application Angular
        // config.addAllowedOrigin("http://localhost:4200");
        
        // Autoriser tous les en-têtes
        config.addAllowedHeader("*");
        
        // Autoriser toutes les méthodes (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");
        
        // Autoriser les cookies
        config.setAllowCredentials(false);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}