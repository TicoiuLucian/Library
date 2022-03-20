package library.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Accountancy {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name="rentalDate", nullable = false, updatable = false)
    private LocalDate rentalDate;

    @Column(name = "returnDate",nullable = false,updatable = false)
    private LocalDate returnDate;
}
