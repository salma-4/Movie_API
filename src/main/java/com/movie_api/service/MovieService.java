package com.movie_api.service;

import com.movie_api.dto.request.MovieRequestDto;
import com.movie_api.dto.response.MovieDetailsResponseDto;
import com.movie_api.dto.response.MovieResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    Page<MovieResponseDto> getAllMovies(Pageable pageable);
    Page<MovieResponseDto> getMoviesByTitle(Pageable pageable ,String title);
    MovieDetailsResponseDto getMovieDetails(Long id);
    String addMovie(MovieRequestDto movieRequestDto);
    void deleteMovie(Long id) ;
    public void batchDeleteMovies(List<Long> movieIds) ;
    public void batchAddMovies(List<MovieRequestDto> movieDtos) ;

    }
