package alessandra_alessandro.ketchapp_bff.utils;

import alessandra_alessandro.ketchapp_bff.models.apicall.UserApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCall {
    public static final String BASE_URL = "http://151.42.71.194:8081/api";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T get(String route, TypeReference<T> typeReference) {
        String url = BASE_URL + route;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return objectMapper.readValue(response.body(), typeReference);
            } else {
                System.err.println("Error: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

public static <T, R> R post(String route, T dto, Class<R> responseType) {
    String url = BASE_URL + route;
    try {
        String requestBody = objectMapper.writeValueAsString(dto);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return objectMapper.readValue(response.body(), responseType);
        } else {
            System.err.println("Error: " + response.statusCode());
            return null;
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        return null;
    }
}
    public static <T> T delete(String route, Class<T> responseType) {
        String url = BASE_URL + route;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return objectMapper.readValue(response.body(), responseType);
            } else {
                System.err.println("Error: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
