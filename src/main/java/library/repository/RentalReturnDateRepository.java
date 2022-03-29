package library.repository;

import library.entity.RentalReturnDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalReturnDateRepository extends JpaRepository<RentalReturnDate, Long> {
}
