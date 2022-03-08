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
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String bookTitle;

    @ManyToMany
    private Set<Author> authors;

    @ManyToOne
    private PublishingHouse publishingHouse;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = BookGenre.class)
    private Set<BookGenre> bookGenre;

    @Column(nullable = false, length = 4)
    private Integer pages;

    @Column(nullable = false, length = 20)
    private String language;

}
