package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.TomatoResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class TomatoesControllers {
    public TomatoResponse createTomato(TomatoResponse tomatoResponse) {
        String url = "/tomatoes";
        TomatoResponse tomato = ApiCall.post(ApiCallUrl.BASE_URL, url, tomatoResponse, TomatoResponse.class);
        if (tomato == null) {
            return null;
        }
        return new TomatoResponse(
                tomato.getId(),
                tomato.getUserUUID(),
                tomato.getStartAt(),
                tomato.getEndAt(),
                tomato.getPauseEnd(),
                tomato.getNextTomatoId(),
                tomato.getSubject(),
                tomato.getCreatedAt()
        );
    }
}
