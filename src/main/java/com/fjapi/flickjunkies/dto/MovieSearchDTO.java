package com.fjapi.flickjunkies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieSearchDTO
{
    public String actorId = null;
    public String actor = null;
    public String title = null;
    public String genre = null;
    public String page = "1";
    public String dateMin = null;
    public String dateMax = null;
    public String ratingMin = null;
    public String ratingMax = null;
    public String voteCount = "500";
    public String language = "en";


}

