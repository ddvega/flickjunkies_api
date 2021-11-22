package com.fjapi.flickjunkies.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.entity.Discover;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Service
public class MovieService
{
    @Autowired
    private String getApiKey;
    private final MovieRepository movieRepository;
    private TmdbService tmdbService;
    // private final Genre genreList = new Genre();

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id)
    {
        if (movieRepository.findById(id).isPresent())
        {
            return movieRepository.findById(id).get();
        }
        return null;
    }

    public String AddMovie(Movie payload) throws JsonProcessingException
    {
        // Movie savedMovie = getMovieById(payload.getId());
//        if (savedMovie != null)
//        {
//
//            System.out.println("Movie with id " + savedMovie.getId() + " already in database.");
//            return "Movie with id " + savedMovie.getId() + " already in database.";
//        } else
//        {

        // movieRepository.insert(payload);
        movieRepository.save(payload);
        Movie addedMovie = getMovieById(payload.getId());
        System.out.println("Movie with id " + addedMovie.getId() + " added to Movie database.");
        return "Movie with id " + addedMovie.getId() + " added to Movie database.";
//        }

    }

    public List<Movie> searchMovies(Map<String, Object> payload) throws IOException
    {
        Discover searchData = new Discover();

        if (payload.containsKey("page")) searchData.setPage(payload.get("page").toString());

        if (payload.containsKey("title"))
        {
            searchData.setTitle(payload.get("title").toString());
            return tmdbService.movieDiscover(searchData, getApiKey);
        }

        if (payload.containsKey("genre")) searchData.setGenre(payload.get("genre").toString());

        if (payload.containsKey("actor"))
            searchData.setActorId(tmdbService.getActorId(payload.get("actor").toString(), getApiKey));

        if (payload.containsKey("release_date_min")) searchData.setDateMIN(payload.get("release_date_min").toString());

        if (payload.containsKey("release_date_max")) searchData.setDateMAX(payload.get("release_date_max").toString());

        if (payload.containsKey("rating_min")) searchData.setRatingMIN(payload.get("rating_min").toString());

        if (payload.containsKey("rating_max")) searchData.setRatingMAX(payload.get("rating_max").toString());

        if (payload.containsKey("language")) searchData.setLanguage(payload.get("language").toString());

        return tmdbService.movieDiscover(searchData, getApiKey);
    }
}

