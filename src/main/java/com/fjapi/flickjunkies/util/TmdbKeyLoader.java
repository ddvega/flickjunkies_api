package com.fjapi.flickjunkies.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TmdbKeyLoader
{
    @Value("${tmdb.key}")
    private String apiKey;

    @Bean
    public String getApiKey() {
        return apiKey;
    }

}

