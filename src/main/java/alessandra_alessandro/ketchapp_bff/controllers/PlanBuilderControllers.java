package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilderResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class PlanBuilderControllers {

    public PlanBuilderResponse createPlanBuilder(PlanBuilderResponse planBuilderResponse) {
        String url = "/plans";
        return ApiCall.post(ApiCallUrl.BASE_URL, url, planBuilderResponse, PlanBuilderResponse.class);
    }
}
