package alessandra_alessandro.ketchapp_bff.models.enums;

public enum ErrorType {
    InternalServerError,
    BlockingError,
    DatabaseError,
    PoolError,
    ValidationError,
    NotFound,
    Conflict,
    Forbidden,
    InternalError;

    @Override
    public String toString() {
        return name();
    }
}

