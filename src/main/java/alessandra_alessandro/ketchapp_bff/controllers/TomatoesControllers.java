package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.TomatoApiCall;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.TomatoResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TomatoesControllers {
    public TomatoResponse convertApiCallToResponse(TomatoApiCall apiCall) {
        if (apiCall == null) {
            return null;
        }
        return new TomatoResponse(
                apiCall.getId(),
                apiCall.getUserUUID(),
                apiCall.getGroupId(),
                apiCall.getStartAt(),
                apiCall.getEndAt(),
                apiCall.getPauseEnd(),
                apiCall.getNextTomatoId(),
                apiCall.getSubject(),
                apiCall.getCreatedAt()
        );
    }
    
    public TomatoApiCall convertResponseToApiCall(TomatoResponse response) {
        if (response == null) {
            return null;
        }
        return new TomatoApiCall(
                response.getId(),
                response.getUserUUID(),
                response.getGroupId(),
                response.getStartAt(),
                response.getEndAt(),
                response.getPauseEnd(),
                response.getNextTomatoId(),
                response.getSubject(),
                response.getCreatedAt()
        );
    }
    
    public TomatoResponse getTomato(UUID uuid) {
        String url = "/tomatoes/" + uuid;
        TomatoApiCall response = ApiCall.get(ApiCallUrl.BASE_URL, url, new TypeReference<>() {
        });
        return convertApiCallToResponse(response);
    }
    
    public TomatoResponse createTomato(TomatoResponse tomatoResponse) {
        String url = "/tomatoes";
        TomatoApiCall response = ApiCall.post(ApiCallUrl.BASE_URL, url, convertResponseToApiCall(tomatoResponse), TomatoApiCall.class);
        return convertApiCallToResponse(response);
    }

}
