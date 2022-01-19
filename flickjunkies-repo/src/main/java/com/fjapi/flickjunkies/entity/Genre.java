package com.fjapi.flickjunkies.entity;

import lombok.*;

import javax.persistence.*;


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

}


