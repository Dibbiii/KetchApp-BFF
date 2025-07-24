package alessandra_alessandro.ketchapp_bff.utils;

import alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse;
import lombok.Getter;

@Getter
public class ApiCallException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public ApiCallException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }
}

