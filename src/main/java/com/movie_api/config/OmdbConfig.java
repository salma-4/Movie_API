package com.movie_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OmdbConfig {

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
