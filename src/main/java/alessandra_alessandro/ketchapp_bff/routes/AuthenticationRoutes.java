package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.AuthenticationControllers;
import alessandra_alessandro.ketchapp_bff.models.requests.LoginRequest;
import alessandra_alessandro.ketchapp_bff.models.requests.RegisterRequest;
import alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.AuthUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRoutes {
    @Autowired
    AuthenticationControllers authenticationControllers;

    @Operation(
            summary = "Login user",
            description = "Authenticates a user and returns user details.",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully authenticated user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthUserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthUserResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        AuthUserResponse authentication = authenticationControllers.login(loginRequest);
        if (authentication.getToken() != null) {
            response.addHeader("Set-Cookie", String.format("token=%s; HttpOnly; Path=/; SameSite=Strict", authentication.getToken()));
        }
        return ResponseEntity.ok(authentication);
    }

    @Operation(
            summary = "Register user",
            description = "Registers a new user and returns user details.",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthUserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<AuthUserResponse> register(@RequestBody RegisterRequest registerRequest, HttpServletResponse response) {
        AuthUserResponse user = authenticationControllers.register(registerRequest);
        if (user.getToken() != null) {
            response.addHeader("Set-Cookie", String.format("token=%s; HttpOnly; Path=/; SameSite=Strict", user.getToken()));
        }
        return ResponseEntity.ok(user);
    }
}
