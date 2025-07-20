package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.PlanBuilderControllers;
import alessandra_alessandro.ketchapp_bff.models.requests.PlanBuilderRequest;
import alessandra_alessandro.ketchapp_bff.models.responses.ErrorResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans")
public class PlanBuilderRoutes {
    @Autowired
    PlanBuilderControllers planBuilderControllers;

    @Operation(
            summary = "Plan Builder API",
            description = "Endpoints for managing plan building operations.",
            tags = {"Plan Builder"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan Builder created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PlanBuilderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<PlanBuilderResponse> createPlanBuilder(@RequestBody PlanBuilderRequest planBuilderResponse) {
        return ResponseEntity.ok(planBuilderControllers.createPlanBuilder(planBuilderResponse));
    }
}