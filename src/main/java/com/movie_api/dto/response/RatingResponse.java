package com.movie_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private  String value ;
}
