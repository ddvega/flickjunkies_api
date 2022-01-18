package com.fjapi.flickjunkies.fjcore.service;

import com.fjapi.flickjunkies.fjcore.entity.Movie;
import com.fjapi.flickjunkies.fjcore.util.ApiClient;
import com.fjapi.flickjunkies.fjcore.util.MovieSearch;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Service
public class TmdbService
{
    private final GenreService genreService;
    private final LanguageService languageService;


    public String getActorId(String searchVal, String apiKey) throws IOException
    {
        String query = "https://api.themoviedb.org/3/search/person?api_key=" + apiKey;
        query += "&language=en-US&query=" + searchVal + "&page=1" + "&include_adult=false";
        JSONObject result = ApiClient.queryApi(query);
        JSONArray res = (JSONArray) result.get("results");
        JSONObject results = (JSONObject) res.get(0);
        return results.get("id").toString();
    }

    public List<Movie> movieDiscover(MovieSearch movieSearch, String apiKey) throws IOException
    {
        String query = buildQuery(movieSearch, apiKey);
        return buildMovieList(query);
    }

    private String buildQuery(MovieSearch movieSearch, String apiKey)
    {
        if (movieSearch.getTitle() != null)
        {
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
    private List<Movie> buildMovieList(String query) throws IOException
    {
        JSONObject result = ApiClient.queryApi(query);
        JSONArray res = (JSONArray) result.get("results");
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < res.length(); i++)
        {
            JSONObject obj = res.getJSONObject(i);
            Movie movie = new Movie();
            movie.setId(longFromJsonObject(obj, "id"));
            movie.setTitle(stringFromJsonObject(obj, "title"));
            movie.setOverview(stringFromJsonObject(obj, "overview"));
            movie.setVote_average(doubleFromJsonObject(obj, "vote_average"));
            movie.setPopularity(doubleFromJsonObject(obj, "popularity"));
            movie.setLanguage(languageService.getLanguageByName(stringFromJsonObject(obj, "original_language")));
            movie.setPoster_path(stringFromJsonObject(obj, "poster_path"));
            movie.setBackdrop_path(stringFromJsonObject(obj, "backdrop_path"));
            movie.setRelease_date(stringFromJsonObject(obj, "release_date"));
            movie.setVote_count(longFromJsonObject(obj, "vote_count"));
            movie.setGenres(genreService.buildGenreList(arrayFromJsonObject(obj, "genre_ids")));
            movieList.add(movie);

        }
        return movieList;
    }

    private Long longFromJsonObject(JSONObject obj, String key)
    {
        return obj.isNull(key) ? null : obj.getLong(key);
    }

    private Double doubleFromJsonObject(JSONObject obj, String key)
    {
        return obj.isNull(key) ? null : obj.getDouble(key);
    }

    private String stringFromJsonObject(JSONObject obj, String key)
    {
        return obj.isNull(key) ? null : obj.getString(key);
    }

    private JSONArray arrayFromJsonObject(JSONObject obj, String key)
    {
        return obj.isNull(key) ? new JSONArray() : obj.getJSONArray(key);
    }


}
