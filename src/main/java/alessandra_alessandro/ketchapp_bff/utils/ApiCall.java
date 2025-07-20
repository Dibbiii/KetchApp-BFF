package alessandra_alessandro.ketchapp_bff.utils;

import alessandra_alessandro.ketchapp_bff.config.TokenHolder;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.enums.ErrorType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCall {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final Logger log = LoggerFactory.getLogger(ApiCall.class);

    /**
     * Sends an HTTP request and parses the response.
     * On success (2xx), returns the parsed response body.
     * On error, throws ApiCallException with a parsed or raw error message.
     */
    private static <R> R sendRequest(HttpRequest request, TypeReference<R> typeReference, Class<R> responseType) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            if (typeReference != null) {
                return objectMapper.readValue(response.body(), typeReference);
            } else if (responseType != null) {
                return objectMapper.readValue(response.body(), responseType);
            }
        } else {
            String message = extractErrorMessage(response.body());
            int code = response.statusCode();
            ErrorType errorType = getErrorType(code, message);
            alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse error = new alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse(code, errorType.name(), message);
            throw new ApiCallException(error);
        }
        return null;
    }

    /**
     * Extracts the error message from a response body.
     * If the body is JSON with a 'message' field, returns that field.
     * Otherwise, returns the raw body.
     */
    private static String extractErrorMessage(String body) {
        try {
            com.fasterxml.jackson.databind.JsonNode node = objectMapper.readTree(body);
            if (node.has("message")) {
                return node.get("message").asText();
            }
        } catch (Exception ignore) {
        }
        return body;
    }

    /**
     * Determines the appropriate ErrorType based on the HTTP status code and error message.
     *
     * @param code    the HTTP status code from the response
     * @param message the error message from the response body
     * @return the corresponding ErrorType enum value
     */
    private static ErrorType getErrorType(int code, String message) {
        if (code == 409 || message.toLowerCase().contains("conflict")) return ErrorType.Conflict;
        if (code == 404 || message.toLowerCase().contains("not found")) return ErrorType.NotFound;
        if (code == 403 || message.toLowerCase().contains("forbidden")) return ErrorType.Forbidden;
        if (message.toLowerCase().contains("validation error")) return ErrorType.ValidationError;
        if (message.toLowerCase().contains("database error")) return ErrorType.DatabaseError;
        if (message.toLowerCase().contains("pool error")) return ErrorType.PoolError;
        if (message.toLowerCase().contains("blocking operation failed")) return ErrorType.BlockingError;
        return ErrorType.InternalError;
    }

    /**
     * Adds the Authorization header with Bearer token if the URL does not contain /api/auth.
     *
     * @param builder the HttpRequest.Builder to modify
     * @param url     the URL of the request
     * @return the modified HttpRequest.Builder
     */
    private static HttpRequest.Builder addAuthHeaderIfNeeded(HttpRequest.Builder builder, String url) {
        if (!url.contains("/api/auth")) {
            String token = TokenHolder.getToken();
            if (token != null && !token.isEmpty()) {
                builder.header("Authorization", "Bearer " + token);
            }
        }
        return builder;
    }

    /**
     * Sends a GET request to the specified API URL and route, and parses the response into the given type.
     *
     * @param apiurl        the base API URL as an ApiCallUrl enum
     * @param route         the specific route to append to the base URL
     * @param typeReference the Jackson TypeReference for deserializing the response
     * @param <T>           the type of the response object
     * @return the parsed response object, or null if the request fails
     */
    public static <T> T get(ApiCallUrl apiurl, String route, TypeReference<T> typeReference) {
        String url = apiurl.toString() + route;
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();
        builder = addAuthHeaderIfNeeded(builder, url);
        HttpRequest request = builder.build();
        try {
            return sendRequest(request, typeReference, null);
        } catch (IOException | InterruptedException e) {
            log.error("GET request failed: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Sends a POST request to the specified API URL and route with the given DTO as the request body,
     * and parses the response into the specified response type.
     *
     * @param apiurl       the base API URL as an ApiCallUrl enum
     * @param route        the specific route to append to the base URL
     * @param dto          the request body object to be serialized as JSON
     * @param responseType the class of the response type for deserialization
     * @param <T>          the type of the request body object
     * @param <R>          the type of the response object
     * @return the parsed response object, or null if the request fails
     */
    public static <T, R> R post(ApiCallUrl apiurl, String route, T dto, Class<R> responseType) {
        String url = apiurl.toString() + route;
        try {
            String requestBody = objectMapper.writeValueAsString(dto);
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody));
            builder = addAuthHeaderIfNeeded(builder, url);
            HttpRequest request = builder.build();
            return sendRequest(request, null, responseType);
        } catch (IOException | InterruptedException e) {
            log.error("POST request failed: {}", e.getMessage());
            return null;
        }
    }
}
