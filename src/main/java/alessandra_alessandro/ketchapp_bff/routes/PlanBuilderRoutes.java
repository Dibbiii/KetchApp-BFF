package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.PlanBuilderControllers;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilderResponse;
import io.swagger.v3.oas.annotations.Operation;
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