package com.fjapi.flickjunkies.model;


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
    private Long id;
    private String title;
    @Column(name = "overview", columnDefinition = "LONGTEXT")
    private String overview;
    private Double vote_average;
    private Double popularity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "languageId")
    private Language language;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private Long vote_count;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genre_map", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;

}

