package alessandra_alessandro.ketchapp_bff.config;

public class TokenHolder {
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public static void setToken(String value) {
        token.set(value);
    }
    public static String getToken() {
        return token.get();
    }
    public static void clear() {
        token.remove();
    }
}
