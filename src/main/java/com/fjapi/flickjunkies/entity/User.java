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
// @Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "string_id_unique", columnNames = "string_id"))
public class User
{
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String password;
    private String role;
    // private Long userId;
//    @Column(name = "string_id", nullable = false)
//    private String stringId;
    // private String email;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Library> libraries;

}
