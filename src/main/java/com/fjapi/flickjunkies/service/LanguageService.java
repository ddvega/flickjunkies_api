package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.entity.Language;
import com.fjapi.flickjunkies.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
@Service
public class LanguageService
{
    private final LanguageRepository languageRepository;

    public Language getLanguageByName(String name)
    {
        Language language = languageRepository.findByLanguageName(name);
        if (language == null)
            return Language.builder().languageName(name).build();
        return language;
    }
}
