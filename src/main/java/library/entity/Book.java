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

    @Column(nullable = false, length = 4)
    private Integer pages;

    @Column(nullable = false, length = 20)
    private String language;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private PublishingHouse publishingHouse;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = BookGenre.class)
    private Set<BookGenre> bookGenre;

}
