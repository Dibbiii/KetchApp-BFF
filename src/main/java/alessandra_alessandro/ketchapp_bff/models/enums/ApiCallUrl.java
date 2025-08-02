package alessandra_alessandro.ketchapp_bff.models.enums;

public enum ApiCallUrl {
    BASE_URL("http://151.42.165.160:8081/api"),
    AUTH_URL("http://192.168.1.210:8083/api");

    private final String url;

    ApiCallUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
