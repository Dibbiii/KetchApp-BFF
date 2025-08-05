package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.TomatoController;
import alessandra_alessandro.ketchapp_bff.models.responses.ActivityResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.TomatoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tomatoes")
public class TomatoesRoutes {

    @Autowired
    TomatoController tomatoesControllers;

    @Operation(
        summary = "Create a new tomato",
        description = "Creates a new tomato entry for a user."
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Successfully created tomato"
            ),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            ),
        }
    )
    @PostMapping
    public ResponseEntity<TomatoResponse> createTomato(
        TomatoResponse tomatoResponse
    ) {
        if (tomatoResponse == null || tomatoResponse.getUserUUID() == null) {
            return ResponseEntity.badRequest().build();
        }
        TomatoResponse createdTomato = tomatoesControllers.createTomato(
            tomatoResponse
        );
        return ResponseEntity.status(201).body(createdTomato);
    }

    @Operation(
        summary = "Get all activities for a tomato",
        description = "Fetches all activities related to a specific tomato by its id.",
        tags = { "Activities" }
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved activities",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ActivityResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Unauthorized (authentication required)"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Tomato or activities not found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            ),
        }
    )
    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityResponse>> getActivities(
        @PathVariable Integer id
    ) {
        List<ActivityResponse> activities = tomatoesControllers.getActivities(
            id
        );
        return ResponseEntity.ok(activities);
    }

    @Operation(
        summary = "Get a tomato",
        description = "Fetches a specific tomato by its id.",
        tags = { "Tomatoes" }
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved tomato",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TomatoResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "401",
                description = "Unauthorized (authentication required)"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Tomato not found"
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error"
            ),
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TomatoResponse> getTomato(@PathVariable Integer id) {
        TomatoResponse tomato = tomatoesControllers.getTomato(id);
        return ResponseEntity.ok(tomato);
    }
}
