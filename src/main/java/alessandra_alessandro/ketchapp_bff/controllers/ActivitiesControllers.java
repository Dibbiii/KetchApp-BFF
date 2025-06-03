package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.ActivityApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.ActivityResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ActivitiesControllers {
    public ActivityResponse convertoApiCallToResponse(ActivityApiCall apiCall) {
        if (apiCall == null) {
            return null;
        }
        return new ActivityResponse(
                apiCall.getId(),
                apiCall.getUserUUID(),
                apiCall.getTomatoId(),
                apiCall.getType(),
                apiCall.getAction(),
                apiCall.getCreatedAt()
        );
    }
    
    public ActivityApiCall convertResponseToApiCall(ActivityResponse response) {
        if (response == null) {
            return null;
        }
        return new ActivityApiCall(
                response.getId(),
                response.getUserUUID(),
                response.getTomatoId(),
                response.getType(),
                response.getAction(),
                response.getCreatedAt()
        );
    }
    
    public List<ActivityResponse> getActivities() {
        String url = "/activities";
        ActivityApiCall[] response = ApiCall.get(url, new TypeReference<ActivityApiCall[]>() {});
        return Stream.of(Objects.requireNonNull(response))
                .map(this::convertoApiCallToResponse)
                .toList();
    }

    public ActivityResponse getActivity(UUID uuid) {
        String url = "/activities/" + uuid;
        ActivityApiCall response = ApiCall.get(url, new TypeReference<ActivityApiCall>() {});
        return convertoApiCallToResponse(response);
    }
    
    public ActivityResponse createActivity(ActivityResponse activityResponse) {
        String url = "/activities";
        ActivityApiCall response = ApiCall.post(url, convertResponseToApiCall(activityResponse), ActivityApiCall.class);
        return convertoApiCallToResponse(response);
    }
    
    public ActivityResponse deleteActivity(UUID uuid) {
        String url = "/activities/" + uuid;
        ActivityApiCall response = ApiCall.delete(url, ActivityApiCall.class);
        return convertoApiCallToResponse(response);
    }
}
