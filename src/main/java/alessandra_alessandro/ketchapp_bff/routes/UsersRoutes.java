package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controller.UsersControllers;
import alessandra_alessandro.ketchapp_bff.models.apicall.FriendApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/users")
@RestController
public class UsersRoutes {
    @Autowired
    UsersControllers usersController;

    @Operation(summary = "Get all users", description = "Fetches a list of all user records.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user records",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<UserApiResponse>> getUsers() {
        List<UserApiResponse> users = usersController.getUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        UserResponse createdUser = usersController.createUser(userRequest);
        if (createdUser == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable UUID uuid) {
        UserResponse deleteUser = usersController.deleteUser(uuid);
        if (deleteUser == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(deleteUser);
    }

    @Operation(summary = "Get user by UUID", description = "Fetches a user record by its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user record",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> getUserByUuid(@PathVariable UUID uuid) {
        UserResponse user = usersController.getUserByUuid(uuid);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get email by username", description = "Fetches the email address associated with a given username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved email address",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Username not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/email/{username}")
    public ResponseEntity<String> getEmailByUsername(@PathVariable String username) {
        UserResponse userResponse = usersController.getEmailByUsername(username);
        if (userResponse == null) {
            return ResponseEntity.status(404).body("Username non trovato");
        }
        if (userResponse.getEmail() == null) {
            return ResponseEntity.status(404).body("Email non trovata per questo username");
        }
        return ResponseEntity.ok(userResponse.getEmail());
    }
    @Operation(summary = "Get user's Tomatoes", description = "Fetches the number of tomatoes for a user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's tomatoes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TomatoResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}/tomatoes")
    public ResponseEntity<List<TomatoResponse>> getUserTomatoes(@PathVariable UUID uuid) {
        List<TomatoResponse> tomatoes = usersController.getUserTomatoes(uuid);
        if (tomatoes == null || tomatoes.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(tomatoes);
    }

    @Operation(summary = "Get activities by user UUID", description = "Fetches a list of activities for a specific user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved activities for user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActivityResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}/activities")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByUserUuid(@PathVariable UUID uuid) {
        List<ActivityResponse> activities = usersController.getActivitiesByUserUuid(uuid);
        if (activities == null || activities.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(activities);
    }

    @Operation(summary = "Get appointments by user UUID", description = "Fetches a list of appointments for a specific user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments for user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppointmentResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}/appointments")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByUserUuid(@PathVariable UUID uuid) {
        List<AppointmentResponse> appointments = usersController.getAppointmentsByUserUuid(uuid);
        if (appointments == null || appointments.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(appointments);
    }

    @Operation(summary = "Get achievements by user UUID", description = "Fetches a list of achievements for a specific user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved achievements for user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AchievementResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}/achievements")
    public ResponseEntity<List<AchievementResponse>> getAchievementsByUserUuid(@PathVariable UUID uuid) {
        List<AchievementResponse> achievements = usersController.getAchievementsByUserUuid(uuid);
        if (achievements == null || achievements.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(achievements);
    }

    @Operation(summary = "Get friends by user UUID", description = "Fetches a list of friends for a specific user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved friends for user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FriendResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}/friends")
    public ResponseEntity<List<FriendResponse>> getFriendsByUserUuid(@PathVariable UUID uuid) {
        List<FriendApiCall> friends = usersController.getFriendsByUserUuid(uuid);
        if (friends == null || friends.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(friends);
    }

}