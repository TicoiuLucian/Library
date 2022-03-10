package library.repository;

import library.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

    ContactDetails findByPhoneNumber(String phoneNumber);
}
