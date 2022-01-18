package fjdata.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "username_unique", columnNames = "username"))
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
    @Column(name = "username", nullable = false)
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
