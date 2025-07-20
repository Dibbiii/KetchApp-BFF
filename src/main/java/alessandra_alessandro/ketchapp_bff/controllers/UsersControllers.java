package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.*;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static alessandra_alessandro.ketchapp_bff.utils.ApiCall.log;

@Service
public class UsersControllers {
    public List<UserResponse> getUsers() {
        String url = "/users";
        UserResponse[] response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
        if (response == null) {
            return List.of();
        }
        return Arrays.asList(response);
    }

    public UserResponse getUser(UUID uuid) {
        String url = "/users/" + uuid;
        return ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
    }

    public String getEmailByUsername(String username) {
        String url = "/users/email/" + username;
        return ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
    }

    public List<ActivityResponse> getUserActivities(UUID uuid) {
        String url = "/users/" + uuid + "/activities";
        ActivityResponse[] response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
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

    public List<AchievementResponse> getUserAchievements(UUID uuid) {
        String url = "/users/" + uuid + "/achievements";
        AchievementResponse[] response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
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

    public StatisticsResponse getUserStatistics(UUID uuid, LocalDate startDate, LocalDate date) {
        log.info("Fetching statistics for user UUID: {} from {} to {}", uuid, startDate, date);
        if (uuid == null) {
            log.error("UUID cannot be null");
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (startDate == null || date == null) {
            log.error("Both startDate and date must be provided");
            throw new IllegalArgumentException("Both startDate and date must be provided");
        }
        String url = "/users/" + uuid + "/statistics?startDate=" + startDate + "&date=" + date;
        return ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid, LocalDate startDate, LocalDate endDate) {
        log.info("Fetching tomatoes for user UUID: {} from {} to {}", uuid, startDate, endDate);
        if (uuid == null) {
            log.error("UUID cannot be null");
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (startDate == null || endDate == null) {
            log.error("Both startDate and endDate must be provided");
            throw new IllegalArgumentException("Both startDate and endDate must be provided");
        }
        String url = "/users/" + uuid + "/tomatoes";
        TomatoResponse[] response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
                .map(tomato -> new TomatoResponse(
                        tomato.getId(),
                        tomato.getUserUUID(),
                        tomato.getStartAt(),
                        tomato.getEndAt(),
                        tomato.getPauseEnd(),
                        tomato.getNextTomatoId(),
                        tomato.getSubject(),
                        tomato.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid, LocalDate date) {
        log.info("Fetching tomatoes for user UUID: {} on date: {}", uuid, date);
        if (uuid == null) {
            log.error("UUID cannot be null");
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (date == null) {
            log.error("Date cannot be null");
            throw new IllegalArgumentException("Date cannot be null");
        }
        String url = "/users/" + uuid + "/tomatoes?date=" + date.toString();
        TomatoResponse[] response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
                .map(tomato -> new TomatoResponse(
                        tomato.getId(),
                        tomato.getUserUUID(),
                        tomato.getStartAt(),
                        tomato.getEndAt(),
                        tomato.getPauseEnd(),
                        tomato.getNextTomatoId(),
                        tomato.getSubject(),
                        tomato.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<TomatoResponse> getUserTomatoes(UUID uuid) {
        log.info("Fetching all tomatoes for user UUID: {}", uuid);
        if (uuid == null) {
            log.error("UUID cannot be null");
            throw new IllegalArgumentException("UUID cannot be null");
        }
        String url = "/users/" + uuid + "/tomatoes";
        TomatoResponse[] response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
        if (response == null) {
            return List.of();
        }
        return Arrays.stream(response)
                .map(tomato -> new TomatoResponse(
                        tomato.getId(),
                        tomato.getUserUUID(),
                        tomato.getStartAt(),
                        tomato.getEndAt(),
                        tomato.getPauseEnd(),
                        tomato.getNextTomatoId(),
                        tomato.getSubject(),
                        tomato.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
