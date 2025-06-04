package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.TomatoesControllers;
import alessandra_alessandro.ketchapp_bff.models.responses.TomatoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tomatoes")
public class TomatoesRoutes {
    @Autowired
    TomatoesControllers tomatoesControllers;
    
    @Operation(summary = "Get all tomatoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tomatoes"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<TomatoResponse>> getTomatoes() {
        List<TomatoResponse> tomatoes = tomatoesControllers.getTomatoes();
        if (tomatoes == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(tomatoes);
    }
    
    @Operation(summary = "Get user's Tomatoes", description = "Fetches the number of tomatoes for a user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's tomatoes"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<TomatoResponse> getTomato(@PathVariable UUID uuid) {
        TomatoResponse tomatoes = tomatoesControllers.getTomato(uuid);
        if (tomatoes == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(tomatoes);
    }
    
    @Operation(summary = "Create a new tomato", description = "Creates a new tomato entry for a user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created tomato"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<TomatoResponse> createTomato(TomatoResponse tomatoResponse) {
        if (tomatoResponse == null || tomatoResponse.getUserUUID() == null) {
            return ResponseEntity.badRequest().build();
        }
        TomatoResponse createdTomato = tomatoesControllers.createTomato(tomatoResponse);
        return ResponseEntity.status(201).body(createdTomato);
    }
    
    @Operation(summary = "Delete a tomato", description = "Deletes a tomato entry by its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted tomato"),
            @ApiResponse(responseCode = "404", description = "Tomato not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<TomatoResponse> deleteTomato(@PathVariable("uuid") UUID uuid) {
        TomatoResponse deletedTomato = tomatoesControllers.deleteTomato(uuid);
        if (deletedTomato == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(deletedTomato);
    }
}
