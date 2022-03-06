package com.fjapi.flickjunkies.repository;

import com.fjapi.flickjunkies.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MovieRepositoryTest
{

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void findMovieById()
    {
        Movie movie = movieRepository.findMovieById(2501L);
        System.out.println(movie);
    }
}