package com.movie_api.controller;

import com.movie_api.dto.request.MovieRequestDto;
import com.movie_api.dto.response.MovieDetailsResponseDto;
import com.movie_api.dto.response.MovieResponseDto;
import com.movie_api.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard-api/v1/admin/movies")
@RequiredArgsConstructor
public class MovieAdminController {
    private final MovieService movieService;
   @GetMapping
   public ResponseEntity< Page<MovieResponseDto>> getAllMovies(@RequestParam Pageable pageable){
        Page<MovieResponseDto> response = movieService.getAllMovies(pageable);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
   @GetMapping("/{id}")
   public ResponseEntity<MovieDetailsResponseDto> getMovieDetails(@PathVariable Long id){
       MovieDetailsResponseDto response = movieService.getMovieDetails(id);
       return new ResponseEntity<>(response,HttpStatus.OK);
   }


    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequest){
        String msg = movieService.addMovie(movieRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){

        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
