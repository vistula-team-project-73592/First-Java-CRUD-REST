package First_Java_CRUD_Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class StatisticsController {

    private final CustomerRepository customerRepository;

    public StatisticsController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/api/customers/statistics")
    public Map<String, Object> getCustomerStatistics() {
        long total = customerRepository.count();
        return Map.of(
                "totalCustomers", total,
                "contributorStudentId", "73991"
        );
    }
}
