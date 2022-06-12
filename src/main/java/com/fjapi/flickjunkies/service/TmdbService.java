package com.fjapi.flickjunkies.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjapi.flickjunkies.dto.MovieSearchDTO;
import com.fjapi.flickjunkies.model.Genre;
import com.fjapi.flickjunkies.model.Movie;
import com.fjapi.flickjunkies.util.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class TmdbService {
    private final LanguageService languageService;

    private final ObjectMapper objectMapper;

    public TmdbService(LanguageService languageService, ObjectMapper objectMapper) {
        this.languageService = languageService;
        this.objectMapper = objectMapper;
    }


    public String getActorId(String searchVal, String apiKey) throws IOException {
        String query = "https://api.themoviedb.org/3/search/person?api_key=" + apiKey;
        query += "&language=en-US&query=" + searchVal + "&page=1" + "&include_adult=false";
        JSONObject result = ApiClient.queryApi(query);
        JSONArray res = (JSONArray) result.get("results");
        JSONObject results = (JSONObject) res.get(0);
        return results.get("id").toString();
    }

    public List<Movie> movieDiscover(MovieSearchDTO movieSearch, String apiKey) throws IOException {
        String query = buildQuery(movieSearch, apiKey);
        return buildMovieList(query);
    }

    private String buildQuery(MovieSearchDTO movieSearch, String apiKey) {
        if (movieSearch.getTitle() != null) {
            String query = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey;
            query += "&page=" + movieSearch.getPage() + "&query=" + movieSearch.getTitle();
            return query;
        }

        String query = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey;
        query += "&page=" + movieSearch.getPage() + "&sort_by=vote_average.desc&include_adult=false&include_video=false";
        if (movieSearch.getRatingMin() != null) query += "&vote_average.gte=" + movieSearch.getRatingMin();
        if (movieSearch.getRatingMax() != null) query += "&vote_average.lte=" + movieSearch.getRatingMax();
        if (movieSearch.getActorId() != null) query += "&with_people=" + movieSearch.getActorId();
        if (movieSearch.getDateMin() != null) query += "&primary_release_date.gte=" + movieSearch.getDateMin();
        if (movieSearch.getDateMax() != null) query += "&primary_release_date.lte=" + movieSearch.getDateMax();
        if (movieSearch.getVoteCount() != null) query += "&vote_count.gte=" + movieSearch.getVoteCount();
        if (movieSearch.getGenre() != null) query += "&with_genres=" + movieSearch.getGenre();
        if (movieSearch.getLanguage() != null) query += "&with_original_language=" + movieSearch.getLanguage();
        return query;
    }

    @NotNull
    private List<Movie> buildMovieList(String query) throws IOException {
        JSONObject result = ApiClient.queryApi(query);
        JSONArray res = (JSONArray) result.get("results");
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < res.length(); i++) {
            JSONObject jsonObject = res.getJSONObject(i);
            Movie movie = objectMapper.readValue(jsonObject.toString(), Movie.class);
            movie.setLanguage(languageService.getLanguageByName(stringFromJsonObject(jsonObject, "original_language")));
            movie.setGenres(Genre.buildGenreList(arrayFromJsonObject(jsonObject, "genre_ids")));
            movieList.add(movie);

        }
        return movieList;
    }

    private String stringFromJsonObject(JSONObject obj, String key) {
        return obj.isNull(key) ? null : obj.getString(key);
    }

    private JSONArray arrayFromJsonObject(JSONObject obj, String key) {
        return obj.isNull(key) ? new JSONArray() : obj.getJSONArray(key);
    }


}
