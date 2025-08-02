package alessandra_alessandro.ketchapp_bff.config;

import alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCallException;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(
        HttpStatus status,
        String message
    ) {
        return new ResponseEntity<>(
            new ErrorResponse(status.value(), status.toString(), message),
            status
        );
    }

    @ExceptionHandler(ApiCallException.class)
    public ResponseEntity<ErrorResponse> handleApiCallException(
        ApiCallException ex
    ) {
        ErrorResponse error = ex.getErrorResponse();
        HttpStatus status = HttpStatus.resolve(error.getCode());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        return buildErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error"
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
        MethodArgumentNotValidException ex
    ) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(
        HttpRequestMethodNotSupportedException ex
    ) {
        return buildErrorResponse(
            HttpStatus.METHOD_NOT_ALLOWED,
            "Method Not Allowed"
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(
        MissingServletRequestParameterException ex
    ) {
        return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Missing Request Parameter"
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(
        HttpMessageNotReadableException ex
    ) {
        return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Malformed JSON request"
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFound(
        NoHandlerFoundException ex
    ) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found");
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
        AuthenticationCredentialsNotFoundException ex
    ) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
        AccessDeniedException ex
    ) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, "Forbidden");
    }
}
