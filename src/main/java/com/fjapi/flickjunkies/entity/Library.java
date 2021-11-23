package com.fjapi.flickjunkies.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "library", uniqueConstraints = @UniqueConstraint(name = "name_unique", columnNames = "library_name"))
public class Library
{
    @Id
    @SequenceGenerator(name = "library_sequence", sequenceName = "library_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "library_sequence")
    private Long libraryId;
    @Column(name = "library_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_library_map", joinColumns = @JoinColumn(name = "library_id", referencedColumnName =
            "libraryId"), inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<Movie> movies;

    public void addMovie(Movie movie)
    {
        movies.add(movie);
    }


}
