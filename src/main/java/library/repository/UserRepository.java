package library.repository;

import library.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUsernameIgnoreCase(String username);

    MyUser findByEmail(String username);

    MyUser findByRandomToken(String randomToken);
}
