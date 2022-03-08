package library.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
public class PublishingHouse {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 4)
    private Integer foundingYear;

    @OneToOne
    private ContactDetails contactDetails;

    @OneToMany(mappedBy = "publishingHouse")
    private Set<Book> books;
}
