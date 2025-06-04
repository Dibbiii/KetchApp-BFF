package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.GroupsControllers;
import alessandra_alessandro.ketchapp_bff.models.responses.GroupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
public class GroupsRoutes {
    @Autowired
    GroupsControllers groupsControllers;

    @Operation(summary = "Get all groups", description = "Fetches a list of all groups.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved groups list"),
            @ApiResponse(responseCode = "204", description = "No groups found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<GroupResponse>> getGroups() {
        List<GroupResponse> groups = groupsControllers.getGroups();
        return ResponseEntity.ok(groups);
    }

    @Operation(summary = "Get a group by UUID", description = "Fetches a group by its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved group"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<GroupResponse> getGroup(UUID uuid) {
        GroupResponse group = groupsControllers.getGroup(uuid);
        if (group == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(group);
    }
    
    @Operation(summary = "Create a new group", description = "Creates a new group with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created group"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@RequestBody GroupResponse groupResponse) {
        GroupResponse createdGroup = groupsControllers.createGroup(groupResponse);
        if (createdGroup == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(201).body(createdGroup);
    }
    
    @Operation(summary = "Delete a group by UUID", description = "Deletes a group by its UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted group"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping
    public ResponseEntity<GroupResponse> deleteGroup(UUID uuid) {
        GroupResponse deletedGroup = groupsControllers.deleteGroup(uuid);
        if (deletedGroup == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(deletedGroup);
    }
}
