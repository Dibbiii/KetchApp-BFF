package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.AchievementApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.AchievementResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class AchievementsControllers {
    
    public AchievementResponse convertApiCallToResponse(AchievementApiCall apiCall) {
        if (apiCall == null) {
            return null;
        }
        return new AchievementResponse(
                apiCall.getId(),
                apiCall.getUserUUID(),
                apiCall.getAchievementNumber(),
                apiCall.getCreatedAt()
        );
    }
    
    public AchievementApiCall convertResponseToApiCall(AchievementResponse response) {
        if (response == null) {
            return null;
        }
        return new AchievementApiCall(
                response.getId(),
                response.getUserUUID(),
                response.getAchievementNumber(),
                response.getCreatedAt()
        );
    }
    
    public List<AchievementResponse> getAchievements() {
        String url = "/achievements";
        AchievementApiCall[] response = ApiCall.get(url, new TypeReference<AchievementApiCall[]>() {});
        return Stream.of(Objects.requireNonNull(response))
                .map(this::convertApiCallToResponse)
                .toList();
    }
    
    public AchievementResponse getAchievement(UUID uuid) {
        String url = "/achievements/" + uuid;
        AchievementApiCall response = ApiCall.get(url, new TypeReference<AchievementApiCall>() {});
        return convertApiCallToResponse(response);
    }
    
    public AchievementResponse createAchievement(AchievementResponse achievementResponse) {
        String url = "/achievements";
        AchievementApiCall response = ApiCall.post(url, convertResponseToApiCall(achievementResponse), AchievementApiCall.class);
        return convertApiCallToResponse(response);
    }
    
    public AchievementResponse deleteAchievement(UUID uuid) {
        String url = "/achievements/" + uuid;
        AchievementApiCall response = ApiCall.delete(url, AchievementApiCall.class);
        return convertApiCallToResponse(response);
    }
    
    
    
}
