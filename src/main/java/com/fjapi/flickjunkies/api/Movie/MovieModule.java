package com.fjapi.flickjunkies.api.Movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.dto.MovieSearchDTO;
import com.fjapi.flickjunkies.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/movie")
public interface MovieModule
{
    @GetMapping("/all")
    List<Movie> fetchAllMovies();

    @PostMapping("/tmdb")
    List<Movie> searchTmdb(@RequestBody MovieSearchDTO payload) throws IOException;

    @PostMapping
    String addMovie(@RequestBody Movie payload) throws JsonProcessingException;
}
