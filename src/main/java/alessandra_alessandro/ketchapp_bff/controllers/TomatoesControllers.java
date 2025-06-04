package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.TomatoApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.TomatoResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

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
    
    public List<TomatoResponse> getTomatoes() {
        String url = "/tomatoes";
        TomatoApiCall[] response = ApiCall.get(url, new TypeReference<TomatoApiCall[]>() {});
        return Stream.of(Objects.requireNonNull(response))
                .map(this::convertApiCallToResponse)
                .toList();
    }
    
    public TomatoResponse getTomato(UUID uuid) {
        String url = "/tomatoes/" + uuid;
        TomatoApiCall response = ApiCall.get(url, new TypeReference<TomatoApiCall>() {});
        return convertApiCallToResponse(response);
    }
    
    public TomatoResponse createTomato(TomatoResponse tomatoResponse) {
        String url = "/tomatoes";
        TomatoApiCall response = ApiCall.post(url, convertResponseToApiCall(tomatoResponse), TomatoApiCall.class);
        return convertApiCallToResponse(response);
    }
    
    public TomatoResponse deleteTomato(UUID uuid) {
        String url = "/tomatoes/" + uuid;
        TomatoApiCall response = ApiCall.delete(url, TomatoApiCall.class);
        return convertApiCallToResponse(response);
    }
}
