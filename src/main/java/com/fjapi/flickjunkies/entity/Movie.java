package com.fjapi.flickjunkies.entity;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie")
public class Movie
{
    @Id
//    @SequenceGenerator(
//            name = "movie_sequence",
//            sequenceName = "movie_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "movie_sequence"
//    )
    private Long id;
    private String title;
    private String overview;
    private Double vote_average;
    private Double popularity;
    private String original_language;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private Long vote_count;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_genre_map",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private List<Genre> genre_ids;


    @Override
    public String toString()
    {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", original_language='" + original_language + '\'' +
                ", genre_ids=" + genre_ids +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", vote_count='" + vote_count + '\'' +
                '}';
    }

    public String toJson()
    {
        // convert array of integers to JsonElement
        JsonElement genreList = new GsonBuilder().create().toJsonTree(this.genre_ids);

        // Build Json Object
        JsonObject movieJson = new JsonObject();
        movieJson.addProperty("id", this.id);
        movieJson.add("genre_ids", genreList);
        movieJson.addProperty("title", this.title);
        movieJson.addProperty("release_date",this.overview);
        movieJson.addProperty("vote_average", this.vote_average);
        movieJson.addProperty("original_language",this.original_language);
        movieJson.addProperty("overview",this.overview);
        movieJson.addProperty("poster_path",this.poster_path);
        movieJson.addProperty("backdrop_path", this.id);
        movieJson.addProperty("vote_count", this.vote_count);

        return movieJson.toString();
    }
}

