package com.movie_api.repository;

import com.movie_api.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long > {

    Optional<Movie> findByImdbID(String imdbID);
}
