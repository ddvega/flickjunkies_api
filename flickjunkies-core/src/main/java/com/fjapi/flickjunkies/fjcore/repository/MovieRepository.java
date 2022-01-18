package com.fjapi.flickjunkies.fjcore.repository;

import com.fjapi.flickjunkies.fjcore.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>
{
    Movie findMovieById(Long id);

}
