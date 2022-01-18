package com.fjapi.flickjunkies.fjcore.repository;

import com.fjapi.flickjunkies.fjcore.entity.Library;
import com.fjapi.flickjunkies.fjcore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long>
{
    Library getLibraryByName(String name);
    void removeAllByUserEquals(User user);
}
