package com.movie_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDto {
    private Long id ;
    private String poster ;
    private String title ;
    private String runTime;
    private String year ;
    private String genre ;


}
