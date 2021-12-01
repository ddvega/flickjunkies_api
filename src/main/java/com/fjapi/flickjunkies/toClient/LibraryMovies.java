package com.fjapi.flickjunkies.toClient;

import com.fjapi.flickjunkies.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryMovies
{
    private Long libraryId;
    private String name;
    private String username;
    private Long userId;
    private Integer movieCount;
    private Set<Movie> movies;
}
