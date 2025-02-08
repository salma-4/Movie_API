package com.movie_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all routes
                .allowedOrigins("http://localhost:4200") // Allow frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed methods
                .allowCredentials(true); // Allow credentials if needed
    }
}