package library.rest;

import library.entity.*;
import library.exeptions.BookOutOfStock;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private RentalReturnDateRepository rentalReturnDateRepository;

    @PostMapping(value = "/book")
    public String saveBook(@RequestBody Book book) {

        final PublishingHouse publishingHouse = publishingHouseRepository.findByName(book.getPublishingHouse().getName());
        if (publishingHouse != null) {
            book.setPublishingHouse(publishingHouse);
        } else {
            book.setPublishingHouse(publishingHouseRepository.save(book.getPublishingHouse()));
        }


        //Luam fiecare author din book-ul primit de la client(Postman)
        for (Author author : book.getAuthors()) {
            //Verificam pe baza phoneNumber-ului din author daca exista contactul respectiv in DB
            ContactDetails foundContact = contactDetailsRepository.findByPhoneNumber(author.getContactDetails().getPhoneNumber());
            if (foundContact != null) {
                //Daca exista, il aducem impreuna cu id-ul sau si il setam pe author
                //+ luam si id-ul author-ului din contactDetails si il setam pe authorul primit de la client
                author.setContactDetails(foundContact);
                author.setId(foundContact.getAuthor().getId());
            } else {
                //Daca nu exista, salvam contact details (primim id pentru el in urma salvarii) in DB si apoi
                // il setam pe author
                //ulterior salvam si authorul
                author.setContactDetails(contactDetailsRepository.save(author.getContactDetails()));
                authorRepository.save(author);
            }
        }

        bookRepository.save(book);

        return "all-books";
    }

    @GetMapping(value = "/book/all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "all-books";
    }

    @DeleteMapping(value = "/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        //TODO Handle delete
        bookRepository.deleteById(id);
    }

    @GetMapping(value = "/book/title")
    public Set<Book> getBookByName(@RequestParam(name = "title") String bookTitle) {
        return bookRepository.findAByBookTitleContaining(bookTitle);
    }

    @GetMapping(value = "/book/save")
    public String saveBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-books";
    }

    @PostMapping(value = "/book/save")
    public String saveBook(@ModelAttribute("book") @RequestBody Book book, RedirectAttributes redirectAttributes) {
        if(book.getQuantity() > 0){
            book.setAvailable(true);
        }else if(book.getQuantity() == 0){
            book.setAvailable(false);
        }
        bookRepository.save(book);
        redirectAttributes.addFlashAttribute("message", "The book has been saved successfully.");
        return "redirect:/book/save";
    }
    @GetMapping(value = "/book/rent/{id}")
    public String rentBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.getById(id);
        RentalReturnDate rentalReturnDate = new RentalReturnDate();
        rentalReturnDate.setReturnDate(rentalReturnDate.getRentalDate().plusDays(30));
        int[] quatity = {0, 1, 2, 3};
        for (int i = 0; i < quatity.length; i++) {
            if (book.getQuantity() == quatity[0]) {
                model.addAttribute("message", "Out of stock!");
            } else if (book.getQuantity() == quatity[1]) {
                model.addAttribute("message1", "Last in stock!");
            } else if (book.getQuantity() == quatity[2]) {
                model.addAttribute("message2", "Last 2 in stock!");
            } else if (book.getQuantity() == quatity[3]) {
                model.addAttribute("message3", "Last 3 in stock");
            }
        }
        model.addAttribute("rentalReturnDate", rentalReturnDate);
        model.addAttribute("book", book);
        return "rent-book";
    }

    @PostMapping(value = "/book/rent/{id}")
    public String rentBook(@PathVariable("id") Long id, @ModelAttribute("rentalReturnDate")
    @RequestBody RentalReturnDate rentalReturnDate, RedirectAttributes redirectAttributes) {
        rentalReturnDate.setReturnDate(rentalReturnDate.getRentalDate().plusDays(30));
        rentalReturnDateRepository.save(rentalReturnDate);
        Book book = bookRepository.getById(id);
        book.setRentalReturnDate(rentalReturnDate);
        try {
            if (book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
            } else {
                throw new BookOutOfStock("The book is out of stock!");
            }
        } catch (BookOutOfStock boos) {
            System.out.println(boos);
        }
        if(book.getQuantity() == 0){
            book.setAvailable(false);
        }
        bookRepository.save(book);
        redirectAttributes.addFlashAttribute("message4", "The book has been rented successfully.");
        return "redirect:/book/all";
    }

    @GetMapping(value = "/book/{bookGenre}")
    public String getBookByGenre(@PathVariable("bookGenre") BookGenre bookGenre, Model model) {
        Set<Book> books = bookRepository.findByBookGenre(bookGenre);
        if(books != null) {
            model.addAttribute("books", books);
            return "all-books";
        }else{
            return "all-books";
        }
    }

    @GetMapping("/book/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookRepository.getById(id));
        return "update-book";
    }

    @RequestMapping("/book/update/{id}")
    public String updateBook(@PathVariable("id") Long id,@ModelAttribute Book newBook, Model model) {
        Book oldBook = bookRepository.getById(id);
        try {
            if (newBook.getBookTitle() != null) {
                oldBook.setBookTitle(newBook.getBookTitle());
            }
            if (newBook.getPages() != null) {
                oldBook.setPages(newBook.getPages());
            }
            if (newBook.getLanguage() != null) {
                oldBook.setLanguage(newBook.getLanguage());
            }
            if (newBook.getQuantity() != null) {
                if(newBook.getQuantity() == 0){
                    oldBook.setAvailable(false);
                }else{
                    oldBook.setAvailable(true);
                }
                oldBook.setQuantity(newBook.getQuantity());
            }
            if (newBook.getBookGenre() != null) {
                oldBook.setBookGenre(newBook.getBookGenre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("book", oldBook);
        bookRepository.save(oldBook);
        return "redirect:/book/all";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:/book/all";
    }


    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping(value ="/book/test")
    public String test(){
        return "test";
    }
}
