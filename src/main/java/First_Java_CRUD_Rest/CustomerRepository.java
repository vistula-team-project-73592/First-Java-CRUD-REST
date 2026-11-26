package First_Java_CRUD_Rest;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<First_Java_CRUD_Rest.Customer, Long> {
    List<First_Java_CRUD_Rest.Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}