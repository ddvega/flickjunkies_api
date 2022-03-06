package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.model.Language;
import com.fjapi.flickjunkies.repository.LanguageRepository;
import org.springframework.stereotype.Service;


@Service
public class LanguageService
{
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language getLanguageByName(String name)
    {
        Language language = languageRepository.findByLanguageName(name);
        if (language == null)
            return Language.builder().languageName(name).build();
        return language;
    }
}
