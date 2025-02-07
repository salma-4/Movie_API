package com.movie_api.Mapper;

import com.movie_api.dto.request.MovieRequestDto;
import com.movie_api.dto.response.MovieDetailsResponseDto;
import com.movie_api.dto.response.MovieResponseDto;
import com.movie_api.entity.Movie;

public interface MovieMapper {

    Movie toMovieEntity(MovieRequestDto movieRequest);
    MovieResponseDto toMovieResponseDto(Movie movie);
    MovieDetailsResponseDto toMovieDetailsResponseDto(Movie movie);
}
