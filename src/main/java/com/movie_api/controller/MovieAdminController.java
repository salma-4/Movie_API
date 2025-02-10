package com.movie_api.controller;

import com.movie_api.dto.request.MovieRequestDto;
import com.movie_api.dto.response.MovieDetailsResponseDto;
import com.movie_api.dto.response.MovieResponseDto;
import com.movie_api.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard-api/v1/admin/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MovieAdminController {
    private final MovieService movieService;
   @GetMapping
   public ResponseEntity< Page<MovieResponseDto>> getAllMovies(@PageableDefault Pageable pageable){
        Page<MovieResponseDto> response = movieService.getAllMovies(pageable);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
   @GetMapping("/{id}")
   public ResponseEntity<MovieDetailsResponseDto> getMovieDetails(@PathVariable Long id){
       MovieDetailsResponseDto response = movieService.getMovieDetails(id);
       return new ResponseEntity<>(response,HttpStatus.OK);
   }


    @PostMapping
    public ResponseEntity<Map<String, String>> addMovie(@RequestBody MovieRequestDto movieRequest){
        String msg = movieService.addMovie(movieRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message",msg);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){

        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/batch-delete")
    public ResponseEntity<String> batchDeleteMovies(@RequestBody List<Long> movieIds) {
        movieService.batchDeleteMovies(movieIds);
        return ResponseEntity.ok("Movies deleted successfully");
    }

    @PostMapping("/batch-add")
    public ResponseEntity<String> batchAddMovies(@RequestBody List<MovieRequestDto> movieDtos) {
        movieService.batchAddMovies(movieDtos);
        return ResponseEntity.ok("Movies added successfully");
    }
}
