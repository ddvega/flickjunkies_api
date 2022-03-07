package com.fjapi.flickjunkies.api.Genre;

import com.fjapi.flickjunkies.model.Genre;
import com.fjapi.flickjunkies.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/genre")
public interface GenreModule {
    @GetMapping("/all")
    List<Genre> fetchAllGenres();

    @GetMapping("/id/{genreId}")
    List<Movie> getMoviesByGenre(@PathVariable("genreId") Integer genreId);
}
