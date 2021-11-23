package com.fjapi.flickjunkies.repository;

import com.fjapi.flickjunkies.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long>
{
    Library getLibraryByName(String name);
}
