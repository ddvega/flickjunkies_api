package com.fjapi.flickjunkies.fjcore.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "language", uniqueConstraints = @UniqueConstraint(name = "name_unique", columnNames =
        "language_name"))
public class Language
{
    @Id
        @SequenceGenerator(
            name = "language_sequence",
            sequenceName = "language_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "language_sequence"
    )
    private Long languageId;
    @Column(name = "language_name", nullable = false)
    private String languageName;
}
