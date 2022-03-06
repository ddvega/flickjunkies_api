package com.fjapi.flickjunkies.dto;

import com.fjapi.flickjunkies.model.Library;
import com.fjapi.flickjunkies.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LibraryDTO {
    private Long libraryId;
    private String name;
    private String username;
    private Long userId;
    private Integer count;
    private Set<Movie> movies;

    public static LibraryDTO buildLibraryDTO(Library library) {
        return LibraryDTO.builder()
                .libraryId(library.getLibraryId())
                .name(library.getName())
                .username(library.getUser().getUsername())
                .userId(library.getUser().getId())
                .count(library.getCount())
                .movies(library.getMovies())
                .build();
    }
}
