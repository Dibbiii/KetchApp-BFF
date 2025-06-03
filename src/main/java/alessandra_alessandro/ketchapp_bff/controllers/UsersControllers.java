package alessandra_alessandro.ketchapp_bff.controllers;

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

    public List<UserResponse> getUsers() {
        String url = "/users";
        List<UserApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch users from API";
        return response.stream()
                .map(user -> new UserResponse(
                        user.getUuid(),
                        user.getUsername(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public UserResponse createUser(UserResponse UserResponse) {
        String url = "/users";
        UserApiCall response = ApiCall.post(url);
        assert response != null : "Failed to create user with request: " + UserResponse;
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public UserResponse deleteUser(UUID uuid) {
        String url = "/users/" + uuid;
        UserApiCall response = ApiCall.delete(url);
        assert response != null : "Failed to delete user with UUID: " + uuid;
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public UserResponse getUserByUuid(UUID uuid) {
        String url = "/users/" + uuid;
        UserApiCall response = ApiCall.get(url);
        assert response != null : "Failed to fetch user by UUID: " + uuid;
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public UserResponse getEmailByUsername(String username) {
        String url = "/users/email/" + username;
        UserApiCall response = ApiCall.get(url);
        assert response != null : "Failed to fetch email for username: " + username;
        assert response.getEmail() != null : "Email not found for username: " + username;
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid) {
        String url = "/users/" + uuid + "/tomatoes";
        List<TomatoApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch tomatoes for user UUID: " + uuid;
        return response.stream()
                .map(tomato -> new TomatoResponse(
                        tomato.getId(),
                        tomato.getUserUUID(),
                        tomato.getGroupId(),
                        tomato.getStartAt(),
                        tomato.getEndAt(),
                        tomato.getPauseEnd(),
                        tomato.getNextTomatoId(),
                        tomato.getSubject(),
                        tomato.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<ActivityResponse> getActivitiesByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/activities";
        List<ActivityApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch activities for user UUID: " + uuid;
        return response.stream()
                .map(activity -> new ActivityResponse(
                        activity.getId(),
                        activity.getUserUUID(),
                        activity.getTomatoId(),
                        activity.getType(),
                        activity.getAction(),
                        activity.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<AppointmentResponse> getAppointmentsByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/appointments";
        List<AppointmentApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch appointments for user UUID: " + uuid;
        return response.stream()
                .map(appointment -> new AppointmentResponse(
                        appointment.getId(),
                        appointment.getUserUUID(),
                        appointment.getName(),
                        appointment.getStartAt(),
                        appointment.getEndAt(),
                        appointment.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<AchievementResponse> getAchievementsByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/achievements";
        List<AchievementApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch achievements for user UUID: " + uuid;
        return response.stream()
                .map(achievement -> new AchievementResponse(
                        achievement.getId(),
                        achievement.getUserUUID(),
                        achievement.getAchievementNumber(),
                        achievement.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<FriendResponse> getFriendsByUserUuid(UUID uuid) {
        String url = "/users/" + uuid + "/friends";
        List<FriendApiCall> response = ApiCall.get(url);
        assert response != null : "Failed to fetch friends for user UUID: " + uuid;
        return response.stream()
                .map(friend -> new FriendResponse(
                        friend.getId(),
                        friend.getUserUUID(),
                        friend.getFriendUUID(),
                        friend.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
