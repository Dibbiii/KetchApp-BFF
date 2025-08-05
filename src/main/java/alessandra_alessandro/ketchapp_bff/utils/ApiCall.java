package alessandra_alessandro.ketchapp_bff.utils;

import alessandra_alessandro.ketchapp_bff.config.TokenHolder;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse;
import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ApiCall {

    private static final Logger log = (Logger) LoggerFactory.getLogger(
        ApiCall.class
    );

    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * Effettua una chiamata HTTP POST con supporto per il token Bearer.
     *
     * @param baseUrl       Base URL dell'API
     * @param url           Endpoint relativo
     * @param request       Corpo della richiesta
     * @param responseType  Classe della risposta attesa
     * @param <T>           Tipo della risposta
     * @return              Oggetto di tipo T come risposta
     */
    public static <T> T post(
        ApiCallUrl baseUrl,
        String url,
        Object request,
        Class<T> responseType
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = TokenHolder.getToken();
        if (token != null && !token.isEmpty() && url != "/login") {
            headers.setBearerAuth(token);
        }

        HttpEntity<Object> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<T> response = restTemplate.exchange(
                baseUrl + url,
                HttpMethod.POST,
                entity,
                responseType
            );
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            int code = ex.getRawStatusCode();
            String error = ex.getStatusText();

            throw new ApiCallException(new ErrorResponse(code, error, error));
        }
    }

    /**
     * Effettua una chiamata HTTP GET con supporto per il token Bearer.
     *
     * @param baseUrl       Base URL dell'API
     * @param url           Endpoint relativo
     * @param typeReference TypeReference per la deserializzazione della risposta
     * @param <T>           Tipo della risposta
     * @return              Oggetto di tipo T come risposta
     */
    public static <T> T get(String baseUrl, String url, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = TokenHolder.getToken();
        log.info("Bearer token: {}", token);
        if (token != null && !token.isEmpty() && url != "/login") {
            headers.setBearerAuth(token);
        }
        log.info(
            "GET request to {} with headers {} with Bearer token: {}",
            baseUrl + url,
            headers,
            token
        );

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<T> response = restTemplate.exchange(
                baseUrl + url,
                HttpMethod.GET,
                entity,
                responseType
            );
            log.info("GET response status code: {}", response);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            int code = ex.getRawStatusCode();
            String error = ex.getStatusText();
            String message = ex.getResponseBodyAsString();
            log.info("GET response error: {}", message);
            throw new ApiCallException(new ErrorResponse(code, error, message));
        }
    }
}
