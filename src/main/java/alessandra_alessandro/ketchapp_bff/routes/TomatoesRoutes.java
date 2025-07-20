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
}
