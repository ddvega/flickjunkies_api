package com.fjapi.flickjunkies.repository;

import com.fjapi.flickjunkies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>
{}
