package com.fjapi.flickjunkies.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.dto.MovieSearchDTO;
import com.fjapi.flickjunkies.model.Movie;
import com.fjapi.flickjunkies.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class MovieService {
    @Value("${tmdb.key}")
    private String apiKey;

    private final MovieRepository movieRepository;
    private final TmdbService tmdbService;

    public MovieService(MovieRepository movieRepository, TmdbService tmdbService) {
        this.movieRepository = movieRepository;
        this.tmdbService = tmdbService;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        log.info("Getting movie object for id " + id + ".");
        if (movieRepository.findById(id).isPresent()) {
            return movieRepository.findById(id).get();
        }
        return null;
    }

    public String AddMovie(Movie movie) throws JsonProcessingException {
        log.info("Adding " + movie.getTitle() + " to database.");
        movieRepository.save(movie);
        Movie addedMovie = getMovieById(movie.getId());
        System.out.println("Movie with id " + addedMovie.getId() + " added to Movie database.");
        return "Movie with id " + addedMovie.getId() + " added to Movie database.";
    }

    public List<Movie> searchMovies(MovieSearchDTO movieSearch) throws IOException {
        log.info("Searching TMDB and returning a list of movies.");

        if (movieSearch.getTitle() != null) {
            return tmdbService.movieDiscover(movieSearch, apiKey);
        }

        if (movieSearch.getActor() != null) {
            movieSearch.setActorId(tmdbService.getActorId(movieSearch.getActor(), apiKey));
        }

        return tmdbService.movieDiscover(movieSearch, apiKey);
    }
}

