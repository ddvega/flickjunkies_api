package com.fjapi.flickjunkies.model;

import com.fjapi.flickjunkies.util.GenreNameIdMap;
import lombok.*;
import org.json.JSONArray;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "genre", uniqueConstraints = @UniqueConstraint(name = "title_unique", columnNames = "genre_name"))
public class Genre
{
    @Id
    private Integer id;
    @Column(name = "genre_name", nullable = false)
    private String name;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "movie_genre_map", joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
//    private List<Movie> movieList;

    @Transient
    public static List<Genre> buildGenreList(JSONArray jsonArray) {
        List<Genre> genreList = new ArrayList<>();
        jsonArray.forEach(genre ->
                genreList.add(Genre.builder().id((Integer) genre).name(GenreNameIdMap.findName((Integer) genre)).build()));
        return genreList;
    }

}


