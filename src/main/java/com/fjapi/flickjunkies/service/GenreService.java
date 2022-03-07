package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.model.Genre;
import com.fjapi.flickjunkies.model.Movie;
import com.fjapi.flickjunkies.repository.GenreRepository;
import com.fjapi.flickjunkies.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GenreService {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;


    public GenreService(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(Integer genreId){
        Genre genre = genreRepository.getById(genreId);
        log.info("genre=" + genre);
        return  movieRepository.findAllByGenresEquals(genre);
    }
}
