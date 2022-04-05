package library.repository;

import library.entity.Book;
import library.entity.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findAByBookTitleContaining(String name);

    Set<Book> findByBookGenre(BookGenre bookGenre);
}
