package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.*;
import alessandra_alessandro.ketchapp_bff.models.responses.*;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersControllers {
    public List<UserResponse> getUsers() {
        String url = "/users";
        UserApiCall[] response = ApiCall.get(url, new TypeReference<UserApiCall[]>() {});
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
                .map(user -> new UserResponse(
                        user.getUuid(),
                        user.getUsername(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public UserResponse createUser(UserResponse UserResponse) {
        String url = "/users";
        UserApiCall response = ApiCall.post(url, UserResponse, UserApiCall.class);
        if (response == null) {
            return null;
        }
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public UserResponse deleteUser(UUID uuid) {
        String url = "/users/" + uuid;
        UserApiCall response = ApiCall.delete(url, UserApiCall.class);
        if (response == null) {
            return null;
        }
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public UserResponse getUserByUuid(UUID uuid) {
        String url = "/users/" + uuid;
        UserApiCall response = ApiCall.get(url, new TypeReference<UserApiCall>() {});
        if (response == null) {
            return null;
        }
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public UserResponse getEmailByUsername(String username) {
        String url = "/users/email/" + username;
        UserApiCall response = ApiCall.get(url, new TypeReference<UserApiCall>() {});
        if (response == null || response.getEmail() == null) {
            return null;
        }
        return new UserResponse(
                response.getUuid(),
                response.getUsername(),
                response.getEmail()
        );
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid) {
        String url = "/users/" + uuid + "/tomatoes";
        TomatoApiCall[] response = ApiCall.get(url, new TypeReference<TomatoApiCall[]>() {});
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
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
        ActivityApiCall[] response = ApiCall.get(url, new TypeReference<ActivityApiCall[]>() {});
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
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
        AppointmentApiCall[] response = ApiCall.get(url, new TypeReference<AppointmentApiCall[]>() {});
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
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
        AchievementApiCall[] response = ApiCall.get(url, new TypeReference<AchievementApiCall[]>() {});
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
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
        FriendApiCall[] response = ApiCall.get(url, new TypeReference<FriendApiCall[]>() {});
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
                .map(friend -> new FriendResponse(
                        friend.getId(),
                        friend.getUserUUID(),
                        friend.getFriendUUID(),
                        friend.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public StatisticsResponse getStatisticsByUserUuid(UUID uuid, LocalDate date) {
        String url = "/users/" + uuid + "/statistics" + "?date=" + date.toString();
        StatisticsApiCall response = ApiCall.get(url, new TypeReference<StatisticsApiCall>() {});
        if (response == null || response.getDates() == null) {
            return null;
        }
        return new StatisticsResponse(
            response.getDates().stream()
                .map(dateApi -> new StatisticsDateResponse(
                    dateApi.getDate(),
                    dateApi.getHours(),
                    dateApi.getSubjects() == null ? List.of() : dateApi.getSubjects().stream()
                        .map(subjectApi -> new StatisticsSubjectResponse(
                            subjectApi.getName(),
                            subjectApi.getHours()
                        ))
                        .collect(Collectors.toList())
                ))
                .collect(Collectors.toList())
        );
    }
}

