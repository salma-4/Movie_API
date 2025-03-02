package com.movie_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imdbID;
    private String title;
    private String year;
    private String rated;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    @Column(length = 1000)
    private String plot;
    private String language;
    private String country;
    private String poster;
    private String imdbRating;
    private String type;

}
