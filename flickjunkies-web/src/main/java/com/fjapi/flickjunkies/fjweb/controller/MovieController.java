package com.fjapi.flickjunkies.fjweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.fjcore.entity.Movie;
import com.fjapi.flickjunkies.fjcore.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController
{

    private final MovieService movieService;

    @GetMapping("/all")
    public List<Movie> fetchAllMovies()
    {
        return movieService.getAllMovies();
    }

    @PostMapping("/tmdb")
    public List<Movie> searchTmdb(@RequestBody Map<String, Object> payload) throws IOException
    {
        return movieService.searchMovies(payload);
    }

    @PostMapping
    public String addMovie(@RequestBody Movie payload) throws JsonProcessingException
    {
        return movieService.AddMovie(payload);
    }
}
