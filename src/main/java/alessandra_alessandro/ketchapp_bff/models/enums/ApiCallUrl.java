package alessandra_alessandro.ketchapp_bff.models.enums;

public enum ApiCallUrl {
    BASE_URL("http://151.61.228.91:8081/api"),
    AUTH_URL("http://151.61.228.91:8083/api");

    private final String url;

    ApiCallUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
