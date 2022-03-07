package com.fjapi.flickjunkies.repository;

import com.fjapi.flickjunkies.model.Genre;
import com.fjapi.flickjunkies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieById(Long id);
    List<Movie> findAllByGenresEquals(Genre genre);

}
