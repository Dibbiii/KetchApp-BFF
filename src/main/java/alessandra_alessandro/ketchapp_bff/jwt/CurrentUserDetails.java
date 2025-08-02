package alessandra_alessandro.ketchapp_bff.jwt;

import java.io.Serializable;
import java.util.UUID;

/**
 * Rappresenta l'utente autenticato, con id (UUID) estratto dal JWT.
 */
public class CurrentUserDetails implements Serializable {

    private final UUID id;

    public CurrentUserDetails(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
