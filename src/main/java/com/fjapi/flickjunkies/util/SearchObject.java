package com.fjapi.flickjunkies.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchObject
{
    public String actorId = null;
    public String title = null;
    public String genre = null;
    public String page = "1";
    public String dateMin = null;
    public String dateMax = null;
    public String ratingMin = null;
    public String ratingMax = null;
    public String voteCount = "100";
    public String language = "en";


}

