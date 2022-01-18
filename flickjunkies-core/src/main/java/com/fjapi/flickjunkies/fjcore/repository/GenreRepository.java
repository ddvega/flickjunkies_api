package com.fjapi.flickjunkies.fjcore.repository;

import com.fjapi.flickjunkies.fjcore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>
{

}
