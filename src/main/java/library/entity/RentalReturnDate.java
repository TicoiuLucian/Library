package library.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
public class RentalReturnDate {

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private final LocalDate rentalDate = LocalDate.now();

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate returnDate;

    @OneToMany(mappedBy = "rentalReturnDate")
    private Set<Book> books;

}
