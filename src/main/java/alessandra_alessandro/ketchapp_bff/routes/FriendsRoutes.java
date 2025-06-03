package alessandra_alessandro.ketchapp_bff.routes;

import alessandra_alessandro.ketchapp_bff.controllers.FriendsControllers;
import alessandra_alessandro.ketchapp_bff.models.responses.FriendResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/friends")
public class FriendsRoutes {
    @Autowired
    FriendsControllers friendsControllers;

    @Operation(summary = "Get all friends", description = "Fetches a list of all friends.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved friends list"),
            @ApiResponse(responseCode = "204", description = "No friends found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<FriendResponse>> getFriends() {
        List<FriendResponse> friends = friendsControllers.getFriends();
        if (friends.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(friends);
    }

    @Operation(summary = "Get a friend by UUID", description = "Fetches a friend's details by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved friend details"),
            @ApiResponse(responseCode = "404", description = "Friend not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<FriendResponse> getFriend(@PathVariable("uuid") UUID uuid) {
        FriendResponse friend = friendsControllers.getFriend(uuid);
        if (friend == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(friend);
    }
    
    @Operation(summary = "Create a new friend", description = "Creates a new friend entry.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created friend"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<FriendResponse> createFriend(@RequestBody FriendResponse friendRequest) {
        if (friendRequest == null || friendRequest.getUserUUID() == null || friendRequest.getFriendUUID() == null) {
            return ResponseEntity.badRequest().build();
        }
        FriendResponse createdFriend = friendsControllers.createFriend(friendRequest);
        return ResponseEntity.status(201).body(createdFriend);
    }
    
    @Operation(summary = "Delete a friend by UUID", description = "Deletes a friend entry by their UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted friend"),
            @ApiResponse(responseCode = "404", description = "Friend not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<FriendResponse> deleteFriend(@PathVariable("uuid") UUID uuid) {
        FriendResponse deletedFriend = friendsControllers.deleteFriend(uuid);
        if (deletedFriend == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedFriend);
    }
}