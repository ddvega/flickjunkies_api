package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TmdbKey
{
    @Value("${tmdb.key}")
    private String apiKey;

    @Bean
    public String getApiKey() {
        return apiKey;
    }

}


