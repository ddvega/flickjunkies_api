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

    @Transient
    public static List<Genre> buildGenreList(JSONArray arr) {
        List<Genre> genreList = new ArrayList<>();
        for (Object a : arr)
            genreList.add(Genre.builder().id((Integer) a).name(GenreNameIdMap.findName((Integer) a)).build());
        return genreList;
    }

}


