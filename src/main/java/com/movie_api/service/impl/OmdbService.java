package com.movie_api.service.impl;

import com.movie_api.config.OmdbConfig;
import com.movie_api.dto.response.OmdbAllDataResponse;
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

    public OmdbAllDataResponse searchMovie(String title, String imdbId, String type, String year, String plot) {

        UriComponentsBuilder urlBuilder =buildUri();

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

        OmdbAllDataResponse omdbAllDataResponse = restTemplate.getForObject(urlBuilder.toUriString(), OmdbAllDataResponse.class);
        if(omdbAllDataResponse.getResponse())
            return omdbAllDataResponse;
        else
            throw new RecordNotFoundException("No Movie found with data you provided" );
    }



    private UriComponentsBuilder buildUri(){
        String url = omdbConfig.getApiUrl() ;
        String apiKey = omdbConfig.getApiKey();
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apikey", apiKey);

    }
}
