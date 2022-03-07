package com.fjapi.flickjunkies.api.Genre;


import com.fjapi.flickjunkies.model.Genre;
import com.fjapi.flickjunkies.model.Movie;
import com.fjapi.flickjunkies.service.GenreService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreModuleImpl implements GenreModule {

    private final GenreService genreService;

    public GenreModuleImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public List<Genre> fetchAllGenres() {
        return genreService.getAllGenres();
    }

    @Override
    public List<Movie> getMoviesByGenre(Integer genreId) {
        return genreService.getMoviesByGenre(genreId);
    }
}
