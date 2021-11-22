package com.fjapi.flickjunkies.entity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class Tmdb
{
    public static String getActorId(String searchVal, String apiKey) throws IOException
    {
        String query = "https://api.themoviedb.org/3/search/person?api_key=" + apiKey;
        query += "&language=en-US&query=" + searchVal + "&page=1" + "&include_adult=false";
        JSONObject result = queryApi(query);
        JSONArray res = (JSONArray) result.get("results");
        JSONObject results = (JSONObject) res.get(0);
        return results.get("id").toString();
    }

    public static List<Movie> movieDiscover(Discover searchData, String apiKey) throws IOException
    {
        if (searchData.getTitle() != null)
        {
            String query = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey;
            query += "&page=" + searchData.getPage() + "&query=" + searchData.getTitle();
            return buildMovieList(query);
        }

        String query = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey;
        query += "&page=" + searchData.getPage() + "&sort_by=popularity.desc&include_adult=false&include_video=false";
        if (searchData.getRatingMIN() != null) query += "&vote_average.gte=" + searchData.getRatingMIN();
        if (searchData.getRatingMAX() != null) query += "&vote_average.lte=" + searchData.getRatingMAX();
        if (searchData.getActorId() != null) query += "&with_people=" + searchData.getActorId();
        if (searchData.getDateMIN() != null) query += "&primary_release_date.gte=" + searchData.getDateMIN();
        if (searchData.getDateMAX() != null) query += "&primary_release_date.lte=" + searchData.getDateMAX();
        if (searchData.getGenre() != null) query += "&with_genres=" + searchData.getGenre();
        if (searchData.getLanguage() != null) query += "&with_original_language=" + searchData.getLanguage();
        return buildMovieList(query);
    }

    @NotNull
    private static List<Movie> buildMovieList(String query) throws IOException
    {
        JSONObject result = queryApi(query);
        JSONArray res = (JSONArray) result.get("results");
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < res.length(); i++)
        {
            JSONObject obj = res.getJSONObject(i);
            Movie movie = new Movie();
            movie.setId(parseLongFromJsonObject(obj, "id"));
            movie.setTitle(parseStringFromJsonObject(obj, "title"));
            movie.setOverview(parseStringFromJsonObject(obj, "overview"));
            movie.setVote_average(parseDoubleFromJsonObject(obj, "vote_average"));
            movie.setPopularity(parseDoubleFromJsonObject(obj, "popularity"));
            movie.setOriginal_language(parseStringFromJsonObject(obj, "original_language"));
            movie.setPoster_path(parseStringFromJsonObject(obj, "poster_path"));
            movie.setBackdrop_path(parseStringFromJsonObject(obj, "backdrop_path"));
            movie.setRelease_date(parseStringFromJsonObject(obj, "release_date"));
            movie.setVote_count(parseLongFromJsonObject(obj, "vote_count"));
            movie.setGenre_ids(GenreMap.buildGenreList(parseGenresJsonObject(obj)));
            movieList.add(movie);

        }
        return movieList;
    }

    private static JSONObject queryApi(String queryString) throws IOException
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(queryString).build();
        Response response = client.newCall(request).execute();
        String jsonData = Objects.requireNonNull(response.body()).string();
        return new JSONObject(jsonData);
    }

    private static Long parseLongFromJsonObject(JSONObject obj, String key)
    {
        return obj.isNull(key) ? null : obj.getLong(key);
    }

    private static Double parseDoubleFromJsonObject(JSONObject obj, String key)
    {
        return obj.isNull(key) ? null : obj.getDouble(key);
    }

    private static String parseStringFromJsonObject(JSONObject obj, String key)
    {
        return obj.isNull(key) ? null : obj.getString(key);
    }

    private static JSONArray parseGenresJsonObject(JSONObject obj)
    {
        return obj.isNull("genre_ids") ? new JSONArray() : obj.getJSONArray("genre_ids");
    }


}
