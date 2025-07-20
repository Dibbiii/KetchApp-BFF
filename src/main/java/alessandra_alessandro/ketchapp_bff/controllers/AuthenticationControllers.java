package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.requests.CreateUserRequest;
import alessandra_alessandro.ketchapp_bff.models.requests.LoginRequest;
import alessandra_alessandro.ketchapp_bff.models.requests.RegisterRequest;
import alessandra_alessandro.ketchapp_bff.models.responses.AuthUserResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import alessandra_alessandro.ketchapp_bff.utils.ApiCallException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthenticationControllers {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationControllers.class);

    public AuthUserResponse login(LoginRequest dto) {
        String url = "/login";
        try {
            AuthUserResponse res = ApiCall.post(ApiCallUrl.AUTH_URL, url, dto, AuthUserResponse.class);
            if (res != null) {
                return res;
            } else {
                log.error("Login failed: response is null");
                throw new ApiCallException(new ErrorResponse(500, "InternalError", "Errore sconosciuto"));
            }
        } catch (ApiCallException ex) {
            ErrorResponse error = ex.getErrorResponse();
            log.error("Login failed: {} {} {}", error.getCode(), error.getError(), error.getMessage());
            throw ex;
        }
    }

    public AuthUserResponse register(RegisterRequest dto) {
        String url = "/register";
        try {
            AuthUserResponse res = ApiCall.post(ApiCallUrl.AUTH_URL, url, dto, AuthUserResponse.class);
            if (res != null) {
                // Call the second API to create the user in the main DB
                CreateUserRequest createUserRequest = new CreateUserRequest(res.getId(), res.getUsername(), res.getEmail());
                Object userDbRes = ApiCall.post(ApiCallUrl.BASE_URL, "/api/users", createUserRequest, Object.class);
                if (userDbRes == null) {
                    log.error("Register failed: user creation in main DB failed");
                    throw new ApiCallException(new ErrorResponse(500, "InternalError", "Errore sconosciuto"));
                }
                return res;
            } else {
                log.error("Register failed: response is null");
                throw new ApiCallException(new ErrorResponse(500, "InternalError", "Errore sconosciuto"));
            }
        } catch (ApiCallException ex) {
            ErrorResponse error = ex.getErrorResponse();
            log.error("Register failed: {} {} {}", error.getCode(), error.getError(), error.getMessage());
            throw ex;
        }
    }
}
