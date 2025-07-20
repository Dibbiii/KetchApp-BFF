package alessandra_alessandro.ketchapp_bff.models.enums;

public enum ApiCallUrl {
    BASE_URL("http://localhost:8081/api"),
    AUTH_URL("http://authentication:8082/api");

    ApiCallUrl(String url) {
    }
}
