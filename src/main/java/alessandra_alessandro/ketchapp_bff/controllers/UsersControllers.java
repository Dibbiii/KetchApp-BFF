package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.*;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UsersControllers {

    public List<UserResponse> getUsers() {
        String url = "/users";
        UserResponse[] response = ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            UserResponse[].class
        );
        if (response == null) {
            return List.of();
        }
        return Arrays.asList(response);
    }

    public UserResponse getUser(UUID uuid) {
        String url = "/users/" + uuid;
        return ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            UserResponse.class
        );
    }

    public String getEmailByUsername(String username) {
        String url = "/users/email/" + username;
        return ApiCall.get(ApiCallUrl.BASE_URL.toString(), url, String.class);
    }

    public List<ActivityResponse> getUserActivities(UUID uuid) {
        String url = "/users/" + uuid + "/activities";
        ActivityResponse[] response = ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            ActivityResponse[].class
        );
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
            .map(activity ->
                new ActivityResponse(
                    activity.getId(),
                    activity.getUserUUID(),
                    activity.getTomatoId(),
                    activity.getType(),
                    activity.getAction(),
                    activity.getCreatedAt()
                )
            )
            .collect(Collectors.toList());
    }

    public List<AchievementResponse> getUserAchievements(UUID uuid) {
        String url = "/users/" + uuid + "/achievements";
        AchievementResponse[] response = ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            AchievementResponse[].class
        );
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
            .map(achievement ->
                new AchievementResponse(
                    achievement.getId(),
                    achievement.getUserUUID(),
                    achievement.getAchievementNumber(),
                    achievement.getCreatedAt()
                )
            )
            .collect(Collectors.toList());
    }

    public StatisticsResponse getUserStatistics(
        UUID uuid,
        LocalDate startDate,
        LocalDate endDate
    ) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException(
                "Both startDate and endDate must be provided"
            );
        }
        String url =
            "/users/" +
            uuid +
            "/statistics?startDate=" +
            startDate +
            "&endDate=" +
            endDate;
        return ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            StatisticsResponse.class
        );
    }

    public List<TomatoResponse> getUserTomatoes(
        UUID uuid,
        LocalDate startDate,
        LocalDate endDate
    ) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException(
                "Both startDate and endDate must be provided"
            );
        }
        StringBuilder urlBuilder = new StringBuilder(
            "/users/" + uuid + "/tomatoes"
        );
        boolean hasQuery = false;
        if (startDate != null) {
            urlBuilder.append(hasQuery ? "&" : "?");
            urlBuilder.append("startDate=").append(startDate);
            hasQuery = true;
        }
        if (endDate != null) {
            urlBuilder.append(hasQuery ? "&" : "?");
            urlBuilder.append("endDate=").append(endDate);
        }
        String url = urlBuilder.toString();
        TomatoResponse[] response = ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            TomatoResponse[].class
        );
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
            .map(tomato ->
                new TomatoResponse(
                    tomato.getId(),
                    tomato.getUserUUID(),
                    tomato.getStartAt(),
                    tomato.getEndAt(),
                    tomato.getPauseEnd(),
                    tomato.getNextTomatoId(),
                    tomato.getSubject(),
                    tomato.getCreatedAt()
                )
            )
            .collect(Collectors.toList());
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid, LocalDate date) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        String url = "/users/" + uuid + "/tomatoes?date=" + date.toString();
        TomatoResponse[] response = ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            TomatoResponse[].class
        );
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
            .map(tomato ->
                new TomatoResponse(
                    tomato.getId(),
                    tomato.getUserUUID(),
                    tomato.getStartAt(),
                    tomato.getEndAt(),
                    tomato.getPauseEnd(),
                    tomato.getNextTomatoId(),
                    tomato.getSubject(),
                    tomato.getCreatedAt()
                )
            )
            .collect(Collectors.toList());
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        String url = "/users/" + uuid + "/tomatoes";
        TomatoResponse[] response = ApiCall.get(
            ApiCallUrl.BASE_URL.toString(),
            url,
            TomatoResponse[].class
        );
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
            .map(tomato ->
                new TomatoResponse(
                    tomato.getId(),
                    tomato.getUserUUID(),
                    tomato.getStartAt(),
                    tomato.getEndAt(),
                    tomato.getPauseEnd(),
                    tomato.getNextTomatoId(),
                    tomato.getSubject(),
                    tomato.getCreatedAt()
                )
            )
            .collect(Collectors.toList());
    }
}
