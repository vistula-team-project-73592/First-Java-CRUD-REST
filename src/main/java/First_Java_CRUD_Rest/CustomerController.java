package First_Java_CRUD_Rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final First_Java_CRUD_Rest.CustomerRepository customerRepository;

    public CustomerController(First_Java_CRUD_Rest.CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<First_Java_CRUD_Rest.Customer>> getAllCustomers(@RequestParam(required = false) String name) {
        if (name != null && !name.isBlank()) {
            List<First_Java_CRUD_Rest.Customer> filtered = customerRepository
                    .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
            return ResponseEntity.ok(filtered);
        }
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<First_Java_CRUD_Rest.Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<First_Java_CRUD_Rest.Customer> createCustomer(@Valid @RequestBody Customer customer) {
        First_Java_CRUD_Rest.Customer saved = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<First_Java_CRUD_Rest.Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer updatedData) {
        return customerRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(updatedData.getFirstName());
                    existing.setLastName(updatedData.getLastName());
                    existing.setEmail(updatedData.getEmail());
                    First_Java_CRUD_Rest.Customer saved = customerRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}