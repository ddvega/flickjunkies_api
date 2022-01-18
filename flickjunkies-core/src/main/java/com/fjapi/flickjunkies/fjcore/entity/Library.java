package com.fjapi.flickjunkies.fjcore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_library_map", joinColumns = @JoinColumn(name = "library_id", referencedColumnName =
            "libraryId"), inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private Set<Movie> movies;
    private Integer count = 0;

    @Transient
    private String username;

    public void addMovie(Movie movie)
    {
        movies.add(movie);
        count += 1;
    }

    public void deleteMovie(Long movieId)
    {
        movies.removeIf(movie1 -> Objects.equals(movie1.getId(), movieId));
        count -= 1;
    }

    public Library summary()
    {
        return Library.builder().libraryId(libraryId).name(name).username(user.getUsername()).count(count).build();
    }

//    public String summary()
//    {
//
//
//        // Build Json Object
//        JsonObject library = new JsonObject();
//        library.addProperty("libraryId", libraryId);
//        library.addProperty("name", name);
//        library.addProperty("username", user.getUsername());
//        library.addProperty("count", count);
//
//
//        return library.toString();
//    }

}