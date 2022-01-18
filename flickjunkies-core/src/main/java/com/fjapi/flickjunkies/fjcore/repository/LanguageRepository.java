package com.fjapi.flickjunkies.fjcore.repository;

import com.fjapi.flickjunkies.fjcore.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>
{
    Language findByLanguageName(String language);
}
