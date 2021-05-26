package cucumber.config.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Profile("cucumber")
public class RestUtils {

    @Autowired
    private TestRestTemplate restTemplate;

    public ResponseEntity<String> get (final String path) {
        return restTemplate.getForEntity(path, String.class);
    }
}
