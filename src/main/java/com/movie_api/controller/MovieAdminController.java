package com.movie_api.controller;

import com.movie_api.dto.response.OmdbResponse;
import com.movie_api.service.impl.OmdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard-api/v1/admin")
@RequiredArgsConstructor
public class MovieAdminController {
    private final OmdbService omdbService;
    @GetMapping("/movie")
    public ResponseEntity<?> searchMovie(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String imdbId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String plot
    ) {
        if ((title == null || title.trim().isEmpty()) && (imdbId == null || imdbId.trim().isEmpty())) {
            return ResponseEntity.badRequest().body("At least one of 'title' or 'imdbId' is required.");
        }
        OmdbResponse response = omdbService.searchMovie(title, imdbId, type, year, plot);
        return ResponseEntity.ok(response);
    }
}
