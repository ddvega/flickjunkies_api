package com.fjapi.flickjunkies.api.Movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.dto.MovieSearchDTO;
import com.fjapi.flickjunkies.model.Movie;
import com.fjapi.flickjunkies.service.MovieService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieModuleImpl implements MovieModule
{
    private final MovieService movieService;

    public MovieModuleImpl(MovieService movieService) {this.movieService = movieService;}

    @Override
    public List<Movie> fetchAllMovies() {return movieService.getAllMovies();}

    @Override
    public List<Movie> searchTmdb(@RequestBody MovieSearchDTO payload) throws IOException
    {
        return movieService.searchMovies(payload);
    }

    @Override
    public String addMovie(@RequestBody Movie payload) throws JsonProcessingException
    {
        return movieService.AddMovie(payload);
    }
}
