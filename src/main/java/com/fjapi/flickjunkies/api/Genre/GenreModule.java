package com.fjapi.flickjunkies.api.Genre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.dto.MovieSearchDTO;
import com.fjapi.flickjunkies.model.Genre;
import com.fjapi.flickjunkies.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/genre")
public interface GenreModule {
    @GetMapping("/all")
    List<Genre> fetchAllGenres();

    @GetMapping("/id/{genreId}")
    List<Movie> getMoviesByGenre(@PathVariable("genreId") Integer genreId);
}
