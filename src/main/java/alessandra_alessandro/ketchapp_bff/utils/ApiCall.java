package alessandra_alessandro.ketchapp_bff.utils;

import alessandra_alessandro.ketchapp_bff.models.apicall.UserApiCall;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCall {
    private static final String BASE_URL = "http://localhost:8081/api";
    private static final String AUTH_URL = "http://authentication:8082/api";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static <R> R sendRequest(HttpRequest request, TypeReference<R> typeReference, Class<R> responseType) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            if (typeReference != null) {
                return objectMapper.readValue(response.body(), typeReference);
            } else if (responseType != null) {
                return objectMapper.readValue(response.body(), responseType);
            }
        } else {
            System.err.println("Error: " + response.statusCode());
        }
        return null;
    }

    public static <T> T get(ApiCallUrl apiurl, String route, TypeReference<T> typeReference) {
        String url = apiurl.toString() + route;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            return sendRequest(request, typeReference, null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T, R> R post(ApiCallUrl apiurl, String route, T dto, Class<R> responseType) {
        String url = apiurl.toString() + route;
        try {
            String requestBody = objectMapper.writeValueAsString(dto);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            return sendRequest(request, null, responseType);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
