package library.rest;

import library.entity.Author;
import library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    //TBD
    @Autowired
    AuthorRepository authorRepository;

    @GetMapping(value = "/author/all")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping(value = "/author")
    public void saveAuthor(@RequestBody Author author) {
        authorRepository.save(author);
    }

}
