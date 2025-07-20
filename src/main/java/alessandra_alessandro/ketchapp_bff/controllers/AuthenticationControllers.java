package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.requests.LoginRequest;
import alessandra_alessandro.ketchapp_bff.models.requests.RegisterRequest;
import alessandra_alessandro.ketchapp_bff.models.responses.UserResponse;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationControllers {
    public UserResponse login(LoginRequest dto) {
        String url = "/login";
        UserResponse res = ApiCall.post(ApiCallUrl.AUTH_URL, url, dto, UserResponse.class);
        if (res != null) {
            return new UserResponse(res.getUserUuid(), res.getEmail(), res.getUsername());
        } else {
            return null;
        }
    }

    public UserResponse register(RegisterRequest dto) {
        String url = "/register";
        UserResponse res = ApiCall.post(ApiCallUrl.AUTH_URL, url, dto, UserResponse.class);
        if (res != null) {
            return new UserResponse(res.getUserUuid(), res.getEmail(), res.getUsername());
        } else {
            return null;
        }
    }
}
