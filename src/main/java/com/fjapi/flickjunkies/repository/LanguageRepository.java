package com.fjapi.flickjunkies.repository;

import com.fjapi.flickjunkies.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long>
{
    Language findByLanguageName(String language);
}
