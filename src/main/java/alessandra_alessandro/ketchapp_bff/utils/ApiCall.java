package alessandra_alessandro.ketchapp_bff.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiCall {
    public static final String BASE_URL = "http://151.42.71.194:8081/api";

    public static<T> T get(String route) {
        String url = BASE_URL + route;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = restTemplate.getForEntity(url, (Class<T>) Object.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }

    public static <T> T post(String route) {
        String url = BASE_URL + route;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = restTemplate.postForEntity(url, null, (Class<T>) Object.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }

    public static <T> T delete(String route) {
        String url = BASE_URL + route;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.DELETE, null, (Class<T>) Object.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }
}
