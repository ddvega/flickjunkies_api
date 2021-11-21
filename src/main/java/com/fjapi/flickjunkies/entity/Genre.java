package com.fjapi.flickjunkies.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "genre", uniqueConstraints = @UniqueConstraint(name = "title_unique", columnNames = "genre_title"))
public class Genre
{
    @Id
    private Integer id;
    @Column(name = "genre_title", nullable = false)
    private String title;

}


