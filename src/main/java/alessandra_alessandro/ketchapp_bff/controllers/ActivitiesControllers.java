package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.ActivityApiCall;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.ActivityResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public ActivityResponse getActivity(UUID uuid) {
        String url = "/activities/" + uuid;
        ActivityApiCall response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
        return convertoApiCallToResponse(response);
    }

    public ActivityResponse createActivity(ActivityResponse activityResponse) {
        String url = "/activities";
        ActivityApiCall response = ApiCall.post(ApiCallUrl.BASE_URL, url, convertResponseToApiCall(activityResponse), ActivityApiCall.class);
        return convertoApiCallToResponse(response);
    }
}
