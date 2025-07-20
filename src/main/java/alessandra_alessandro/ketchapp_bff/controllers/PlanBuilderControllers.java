package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.requests.PlanBuilderRequest;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilderResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class PlanBuilderControllers {

    public PlanBuilderResponse createPlanBuilder(PlanBuilderRequest planBuilderRequest) {
        String url = "/plans";
        PlanBuilderResponse response = ApiCall.post(ApiCallUrl.BASE_URL, url, planBuilderRequest, PlanBuilderResponse.class);
        if (response == null) {
            throw new RuntimeException("Failed to create plan builder: response is null");
        }
        return response;
    }
}
