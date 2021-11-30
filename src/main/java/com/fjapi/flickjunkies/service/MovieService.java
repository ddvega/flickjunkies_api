package com.fjapi.flickjunkies.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.util.MovieSearch;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Service
@Slf4j
public class MovieService
{
    @Value("${tmdb.key}")
    private String apiKey;

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private TmdbService tmdbService;

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id)
    {
        log.info("Getting movie object for id " + id + ".");
        if (movieRepository.findById(id).isPresent())
        {
            return movieRepository.findById(id).get();
        }
        return null;
    }

    public String AddMovie(Movie payload) throws JsonProcessingException
    {
        log.info("Adding " + payload.getTitle() + " to database.");
        movieRepository.save(payload);
        Movie addedMovie = getMovieById(payload.getId());
        System.out.println("Movie with id " + addedMovie.getId() + " added to Movie database.");
        return "Movie with id " + addedMovie.getId() + " added to Movie database.";
    }

    public List<Movie> searchMovies(Map<String, Object> payload) throws IOException
    {
        log.info("Searching TMDB and returning a list of movies.");
        MovieSearch movieSearch = new MovieSearch();

        if (payload.containsKey("page")) movieSearch.setPage(payload.get("page").toString());

        if (payload.containsKey("title"))
        {
            movieSearch.setTitle(payload.get("title").toString());
            return tmdbService.movieDiscover(movieSearch, apiKey);
        }

        if (payload.containsKey("genre")) movieSearch.setGenre(payload.get("genre").toString());

        if (payload.containsKey("actor"))
            movieSearch.setActorId(tmdbService.getActorId(payload.get("actor").toString(), apiKey));

        if (payload.containsKey("date_min")) movieSearch.setDateMin(payload.get("date_min").toString());

        if (payload.containsKey("date_max")) movieSearch.setDateMax(payload.get("date_max").toString());

        if (payload.containsKey("rating_min")) movieSearch.setRatingMin(payload.get("rating_min").toString());

        if (payload.containsKey("rating_max")) movieSearch.setRatingMax(payload.get("rating_max").toString());

        if (payload.containsKey("language")) movieSearch.setLanguage(payload.get("language").toString());

        if (payload.containsKey("vote_count_min")) movieSearch.setVoteCount(payload.get("vote_count_min").toString());

        return tmdbService.movieDiscover(movieSearch, apiKey);
    }
}

