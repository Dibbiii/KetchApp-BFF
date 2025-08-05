package alessandra_alessandro.ketchapp_bff.controllers;

import static alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl.BASE_URL;

import alessandra_alessandro.ketchapp_bff.models.responses.ActivityResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesControllers {

    public ActivityResponse createActivity(ActivityResponse activityResponse) {
        String url = "/activities";
        return ApiCall.post(
            BASE_URL,
            url,
            activityResponse,
            ActivityResponse.class
        );
    }
}
