package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.AchievementsControllers;
import alessandra_alessandro.ketchapp_bff.models.responses.AchievementResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.TomatoResponse;
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

@RestController
@RequestMapping("/api/achievements")
public class AchievementsRoutes {
    @Autowired
    AchievementsControllers achievementsControllers;

    @Operation(summary = "Get user's Tomatoes", description = "Fetches the number of tomatoes for a user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's tomatoes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TomatoResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<AchievementResponse>> getAchievements() {
        List<AchievementResponse> achievements = achievementsControllers.getAchievements();
        if (achievements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(achievements);
    }
    
    @Operation(summary = "Get user's Tomatoes", description = "Fetches the number of tomatoes for a user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's tomatoes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TomatoResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<AchievementResponse> getAchievement(@PathVariable("uuid") UUID uuid) {
        AchievementResponse achievement = achievementsControllers.getAchievement(uuid);
        if (achievement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(achievement);
    }

    @Operation(summary = "Get user's Tomatoes", description = "Fetches the number of tomatoes for a user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's tomatoes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TomatoResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<AchievementResponse> createAchievement(@RequestBody AchievementResponse achievements) {
        AchievementResponse achievement = achievementsControllers.createAchievement(achievements);
        return ResponseEntity.ok(achievement);
    }
    
    @Operation(summary = "Get user's Tomatoes", description = "Fetches the number of tomatoes for a user by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user's tomatoes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TomatoResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<AchievementResponse> deleteAchievement(@PathVariable("uuid") UUID uuid) {
        AchievementResponse achievement = achievementsControllers.deleteAchievement(uuid);
        if (achievement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(achievement);
    }
    
    
    
}
