package library.rest;

import library.entity.Book;
import library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping(value = "/book")
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping(value = "/book/all")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @DeleteMapping(value = "/book/{id}")
    public void deleteBook(@PathVariable Long id){
        bookRepository.deleteById(id);
    }
}
