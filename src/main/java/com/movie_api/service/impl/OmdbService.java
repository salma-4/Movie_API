package com.movie_api.service.impl;

import com.movie_api.config.OmdbConfig;
import com.movie_api.dto.response.OmdbResponse;
import com.movie_api.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class OmdbService {
    private final OmdbConfig omdbConfig;
    private final RestTemplate restTemplate;
    public OmdbResponse searchMovie(String title, String imdbId, String type, String year, String plot) {
        String url = omdbConfig.getApiUrl() ;
        String apiKey = omdbConfig.getApiKey();
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", apiKey);

        if (title != null && !title.trim().isEmpty()) {
            urlBuilder.queryParam("t", title);
        } else if (imdbId != null && !imdbId.trim().isEmpty()) {
            urlBuilder.queryParam("i", imdbId);
        }

        // Add optional parameters
        if(type!=null &&!type.trim().isEmpty()){
            urlBuilder.queryParam("type", type);
        }
        if (year != null && !year.trim().isEmpty()) {
            urlBuilder.queryParam("y", year);
        }
        if(plot!=null &&!plot.trim().isEmpty()) {
            urlBuilder.queryParam("plot", plot);
        }
        String finalUrl = urlBuilder.toUriString();
        System.out.println("Constructed URL: " + finalUrl);

        OmdbResponse omdbResponse = restTemplate.getForObject(urlBuilder.toUriString(), OmdbResponse.class);
        if(omdbResponse.getResponse())
            return omdbResponse;
        else
            throw new RecordNotFoundException("No Movie found with data you provided" );
    }
}
