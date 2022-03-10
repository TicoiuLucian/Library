package library.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false, length = 30)
    private String firstName;

    @OneToOne
    private ContactDetails contactDetails;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

    @Enumerated(EnumType.STRING)
    private BirthPlace birthPlace;

}
