package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.ActivitiesControllers;
import alessandra_alessandro.ketchapp_bff.controllers.PlanBuilderControllers;
import alessandra_alessandro.ketchapp_bff.models.responses.ActivityResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesRoutes {
    @Autowired
    ActivitiesControllers activitiesControllers;

    @Operation(summary = "Get all activities by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activities retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable UUID uuid) {
        ActivityResponse activities = activitiesControllers.getActivity(uuid);
        return ResponseEntity.ok(activities);
    }

    @Operation(summary = "Create a new activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityResponse activityResponse) {
        ActivityResponse createdActivity = activitiesControllers.createActivity(activityResponse);
        return ResponseEntity.status(201).body(createdActivity);
    }

    @RestController
    @RequestMapping("/api/plans")
    public static class PlanBuilderRoutes {
        @Autowired
        PlanBuilderControllers planBuilderControllers;
        
        @Operation(summary = "Plan Builder API", description = "Endpoints for managing plan building operations.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        })
        @PostMapping
        public ResponseEntity<PlanBuilderResponse> createPlanBuilder(@RequestBody PlanBuilderResponse planBuilderResponse) {
            return ResponseEntity.ok(planBuilderControllers.createPlanBuilder(planBuilderResponse));
        }
    }
}