package com.fjapi.flickjunkies.repository;

import com.fjapi.flickjunkies.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>
{

}
