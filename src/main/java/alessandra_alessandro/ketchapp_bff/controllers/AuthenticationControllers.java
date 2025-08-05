package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.config.TokenHolder;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.requests.CreateUserRequest;
import alessandra_alessandro.ketchapp_bff.models.requests.LoginRequest;
import alessandra_alessandro.ketchapp_bff.models.requests.RegisterRequest;
import alessandra_alessandro.ketchapp_bff.models.responses.AuthUserResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import alessandra_alessandro.ketchapp_bff.utils.ApiCallException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationControllers {

    private static final Logger log = LoggerFactory.getLogger(
        AuthenticationControllers.class
    );

    /**
     * Authenticates a user by sending login credentials to the authentication service.
     *
     * @param dto the login request data containing username/email and password
     * @return AuthUserResponse containing authentication and user information
     * @throws ApiCallException if authentication fails or the response is invalid
     */
    public AuthUserResponse login(LoginRequest dto) {
        String url = "/login";
        try {
            AuthUserResponse res = ApiCall.post(
                ApiCallUrl.AUTH_URL,
                url,
                dto,
                AuthUserResponse.class
            );
            if (res != null) {
                TokenHolder.setToken(res.getToken());
                log.info("Bearer token: {}", TokenHolder.getToken());
                return res;
            } else {
                log.error("Login failed: response is null");
                throw new ApiCallException(
                    new ErrorResponse(
                        500,
                        "InternalError",
                        "Errore sconosciuto"
                    )
                );
            }
        } catch (ApiCallException ex) {
            ErrorResponse error = ex.getErrorResponse();
            log.error(
                "Login failed: {} {} {}",
                error.getCode(),
                error.getError(),
                error.getMessage()
            );
            throw ex;
        }
    }

    /**
     * Registers a new user by sending the registration data to the authentication service.
     * If successful, creates the user in the main database using the received token.
     *
     * @param dto the registration request data
     * @return AuthUserResponse containing authentication and user information
     * @throws ApiCallException if registration or user creation fails
     */
    public AuthUserResponse register(RegisterRequest dto) {
        try {
            AuthUserResponse res = ApiCall.post(
                ApiCallUrl.AUTH_URL,
                "/register",
                dto,
                AuthUserResponse.class
            );
            System.out.println("res: " + res);
            if (res != null) {
                TokenHolder.setToken(res.getToken());
                CreateUserRequest createUserRequest = new CreateUserRequest(
                    res.getId(),
                    res.getUsername(),
                    res.getEmail()
                );
                Object userDbRes = ApiCall.post(
                    ApiCallUrl.BASE_URL,
                    "/users",
                    createUserRequest,
                    Object.class
                );
                if (userDbRes == null) {
                    log.error(
                        "Register failed: user creation in main DB failed (/users endpoint)"
                    );
                    throw new ApiCallException(
                        new ErrorResponse(500, "InternalError", "Unknown error")
                    );
                }
                return res;
            } else {
                log.error("Register failed: response is null");
                throw new ApiCallException(
                    new ErrorResponse(500, "InternalError", "Unknown error")
                );
            }
        } catch (ApiCallException ex) {
            ErrorResponse error = ex.getErrorResponse();
            log.error(
                "Register failed: {} {} {}",
                error.getCode(),
                error.getError(),
                error.getMessage()
            );
            throw ex;
        }
    }
}
