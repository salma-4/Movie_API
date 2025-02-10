package com.movie_api.service.impl;

import com.movie_api.Mapper.MovieMapper;
import com.movie_api.dto.request.MovieRequestDto;
import com.movie_api.dto.response.MovieDetailsResponseDto;
import com.movie_api.dto.response.MovieResponseDto;
import com.movie_api.entity.Movie;
import com.movie_api.exception.ConflictException;
import com.movie_api.exception.RecordNotFoundException;
import com.movie_api.repository.MovieRepository;
import com.movie_api.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public Page<MovieResponseDto> getAllMovies(Pageable pageable) {
        Page<Movie> page = movieRepository.findAll(pageable);
        return page.map(movieMapper :: toMovieResponseDto);
    }

    public Page<MovieResponseDto> getMoviesByTitle(Pageable pageable ,String title){
     Page<Movie> movies = movieRepository.findByTitle(title,pageable);

     return movies.map(
             movieMapper :: toMovieResponseDto
     );
    }

    @Override
    public MovieDetailsResponseDto getMovieDetails(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("No movie with id "+id));

        return movieMapper.toMovieDetailsResponseDto(movie);
    }

    @Transactional
    @Override
    public String addMovie(MovieRequestDto movieRequestDto){

        Optional<Movie> existingMovie = movieRepository.findByImdbID(movieRequestDto.getImdbID());
        if(existingMovie.isPresent())
            throw new ConflictException("You have saved this movie before");

        Movie movie = movieMapper.toMovieEntity(movieRequestDto);
        movieRepository.save(movie);

        return "Movie saved successfully";

    }

    @Transactional
    @Override
    public void deleteMovie(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new RecordNotFoundException("No movie with id "+id));
        movieRepository.delete(movie);
    }

    @Transactional
    public void batchDeleteMovies(List<Long> movieIds) {
        movieRepository.deleteMoviesByIds(movieIds);
    }

    @Transactional
    public void batchAddMovies(List<MovieRequestDto> movieDtos) {
        for (MovieRequestDto dto : movieDtos) {
            movieRepository.addMovie(dto.getTitle(), dto.getYear(), dto.getGenre(), dto.getPoster());
        }
    }
}
