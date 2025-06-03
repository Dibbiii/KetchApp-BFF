package alessandra_alessandro.ketchapp_bff.controller;

import alessandra_alessandro.ketchapp_bff.models.apicall.*;
import alessandra_alessandro.ketchapp_bff.models.responses.*;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersControllers {
    @Autowired
    RestTemplate restTemplate;

    public UserApiCall convertResponseToApiCall(UserResponse userResponse) {
        return new UserApiCall(
                userResponse.getUuid(),
                userResponse.getUsername(),
                userResponse.getEmail()
        );
    }

    public UserResponse convertApiCallToResponse(UserApiCall userApiCall) {
        return new UserResponse(
                userApiCall.getUuid(),
                userApiCall.getUsername(),
                userApiCall.getEmail()
        );
    }


    public List<UserResponse> getUsers() {
        String url = "/users";
        List<UserApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch users from API";
        return response.stream()
                .map(this::convertApiCallToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse createUser(UserResponse UserResponse) {
        String url = "/users";
        UserApiCall response = ApiCall.post(url);
        assert response != null : "Failed to create user with request: " + UserResponse;
        return convertApiCallToResponse(response);
    }

    public UserResponse deleteUser(UUID uuid) {
        String url = "/users/" + uuid;
        UserApiCall response = ApiCall.delete(url);
        assert response != null : "Failed to delete user with UUID: " + uuid;
        return convertApiCallToResponse(response);
    }

    public UserResponse getUserByUuid(UUID uuid) {
        String url = "/users/" + uuid;
        UserApiCall response = ApiCall.get(url);
        assert response != null : "Failed to fetch user by UUID: " + uuid;
        return convertApiCallToResponse(response);
    }

    public UserResponse getEmailByUsername(String username) {
        String url = "/users/email/" + username;
        UserApiCall response = ApiCall.get(url);
        assert response != null : "Failed to fetch email for username: " + username;
        assert response.getEmail() != null : "Email not found for username: " + username;
        return convertApiCallToResponse(response);
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid) {
        String url = "/users/" + uuid + "/tomatoes";
        List<TomatoApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch tomatoes for user UUID: " + uuid;
        return response.stream()
                .map(tomato -> new TomatoResponse(
                        tomato.getUuid(),
                        tomato.getUserUuid(),
                        tomato.getCreatedAt(),
                        tomato.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<ActivityResponse> getActivitiesByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/activities";
        List<ActivityApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch activities for user UUID: " + uuid;
        return response;
    }

    public List<AppointmentResponse> getAppointmentsByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/appointments";
        List<AppointmentApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch appointments for user UUID: " + uuid;
        return response;
    }

    public List<AchievementResponse> getAchievementsByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/achievements";
        List<AchievementApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch achievements for user UUID: " + uuid;
        return response;
    }

    public List<FriendApiCall> getFriendsByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/friends";
        List<FriendApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch friends for user UUID: " + uuid;
        return response;
    }
}
