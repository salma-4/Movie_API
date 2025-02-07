package com.movie_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsResponseDto {
    private Long id;
    private String imdbID;
    private String title;
    private String year;
    private String rated;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String poster;
    private String imdbRating;
    private String type;
}
