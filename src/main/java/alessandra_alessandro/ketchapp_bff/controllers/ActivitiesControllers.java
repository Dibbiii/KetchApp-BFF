package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.ActivityResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesControllers {

    public ActivityResponse createActivity(ActivityResponse activityResponse) {
        String url = "/activities";
        return ApiCall.post(ApiCallUrl.BASE_URL, url, activityResponse, ActivityResponse.class);
    }
}
