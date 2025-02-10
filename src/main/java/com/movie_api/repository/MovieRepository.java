package com.movie_api.repository;

import com.movie_api.entity.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long > {

    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Movie> findByTitle(@Param("title") String title, Pageable pageable);

    Optional<Movie> findByImdbID(String imdbID);

    @Modifying
    @Transactional
    @Query("DELETE FROM Movie m WHERE m.id IN :movieIds")
    void deleteMoviesByIds(@Param("movieIds") List<Long> movieIds);

    // Add a new movie using a native SQL query
    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO Movie (title, year, genre, poster) VALUES (:title, :year, :genre, :poster)",
            nativeQuery = true
    )
    void addMovie(
            @Param("title") String title,
            @Param("year") String year,
            @Param("genre") String genre,
            @Param("poster") String poster
    );
}
