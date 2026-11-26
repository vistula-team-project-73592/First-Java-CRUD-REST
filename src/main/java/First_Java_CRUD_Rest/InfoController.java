package First_Java_CRUD_Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @GetMapping("/api/info")
    public Map<String, String> getAppInfo() {
        return Map.of(
                "studentId", "73592",
                "applicationName", "First-Java-CRUD-Rest",
                "version", "1.0.0"
        );
    }
}