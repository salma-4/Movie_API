package com.movie_api.Mapper.impl;

import com.movie_api.Mapper.MovieMapper;
import com.movie_api.dto.request.MovieRequestDto;
import com.movie_api.dto.response.MovieDetailsResponseDto;
import com.movie_api.dto.response.MovieResponseDto;
import com.movie_api.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapperImpl implements MovieMapper {
    @Override
    public Movie toMovieEntity(MovieRequestDto movieRequest) {
        return Movie.builder()
                .title(movieRequest.getTitle())
                .imdbID(movieRequest.getImdbID())
                .year(movieRequest.getYear())
                .rated(movieRequest.getRated())
                .runtime(movieRequest.getRuntime())
                .genre(movieRequest.getGenre())
                .director(movieRequest.getDirector())
                .actors(movieRequest.getActors())
                .plot(movieRequest.getPlot())
                .language(movieRequest.getLanguage())
                .country(movieRequest.getCountry())
                .poster(movieRequest.getPoster())
                .imdbRating(movieRequest.getImdbRating())
                .type(movieRequest.getType())
                .build();
    }

    @Override
    public MovieResponseDto toMovieResponseDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .poster(movie.getPoster())
                .runTime(movie.getRuntime())
                .build();
    }

    @Override
    public MovieDetailsResponseDto toMovieDetailsResponseDto(Movie movie) {
        return MovieDetailsResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .imdbID(movie.getImdbID())
                .year(movie.getYear())
                .rated(movie.getRated())
                .runtime(movie.getRuntime())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .actors(movie.getActors())
                .plot(movie.getPlot())
                .language(movie.getLanguage())
                .country(movie.getCountry())
                .poster(movie.getPoster())
                .imdbRating(movie.getImdbRating())
                .type(movie.getType())
                .build();
    }
}
