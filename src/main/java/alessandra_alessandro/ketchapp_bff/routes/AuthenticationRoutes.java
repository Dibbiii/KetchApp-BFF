package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.AuthenticationControllers;
import alessandra_alessandro.ketchapp_bff.models.requests.LoginRequest;
import alessandra_alessandro.ketchapp_bff.models.requests.RegisterRequest;
import alessandra_alessandro.ketchapp_bff.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        UserResponse authentication = authenticationControllers.login(loginRequest);
        if (authentication != null) {
            return ResponseEntity.ok(authentication);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Register user",
            description = "Registers a new user and returns user details.",
            tags = {"Authentication"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        UserResponse registeredUser = authenticationControllers.register(registerRequest);
        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
