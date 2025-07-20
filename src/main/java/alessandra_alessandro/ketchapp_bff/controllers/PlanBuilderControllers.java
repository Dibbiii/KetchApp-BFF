package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilderApiCall;
import alessandra_alessandro.ketchapp_bff.models.enums.ApiCallUrl;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilderResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class PlanBuilderControllers {
    
    
    public PlanBuilderResponse.PlanBuilderCalendarResponse convertCalendarApiCallToResponse(PlanBuilderApiCall.PlanBuilderCalendarApiCall apicall) {
        return new PlanBuilderResponse.PlanBuilderCalendarResponse(
                apicall.getTitle(),
                apicall.getStart_at(),
                apicall.getEnd_at()
        );
    }

    public PlanBuilderApiCall.PlanBuilderCalendarApiCall convertCalendarResponseToApiCall(PlanBuilderResponse.PlanBuilderCalendarResponse response) {
        return new PlanBuilderApiCall.PlanBuilderCalendarApiCall(
                response.getTitle(),
                response.getStart_at(),
                response.getEnd_at()
        );
    }

    public PlanBuilderResponse.PlanBuilderTomatoesResponse convertTomatoesApiCallToResponse(PlanBuilderApiCall.PlanBuilderTomatoesApiCall apicall) {
        return new PlanBuilderResponse.PlanBuilderTomatoesResponse(
                apicall.getTitle(),
                apicall.getStart_at(),
                apicall.getEnd_at(),
                apicall.getPause_end_at(),
                apicall.getSubject()
        );
    }

    public PlanBuilderApiCall.PlanBuilderTomatoesApiCall convertTomatoesResponseToApiCall(PlanBuilderResponse.PlanBuilderTomatoesResponse response) {
        return new PlanBuilderApiCall.PlanBuilderTomatoesApiCall(
                response.getTitle(),
                response.getStart_at(),
                response.getEnd_at(),
                response.getPause_end_at(),
                response.getSubject()
        );
    }

    public PlanBuilderResponse.PlanBuilderRulesResponse convertRulesApiCallToResponse(PlanBuilderApiCall.PlanBuilderRulesApiCall apicall) {
        return new PlanBuilderResponse.PlanBuilderRulesResponse(
                apicall.getTitle(),
                apicall.getStart_at(),
                apicall.getEnd_at()
        );
    }

    public PlanBuilderApiCall.PlanBuilderRulesApiCall convertRulesResponseToApiCall(PlanBuilderResponse.PlanBuilderRulesResponse response) {
        return new PlanBuilderApiCall.PlanBuilderRulesApiCall(
                response.getTitle(),
                response.getStart_at(),
                response.getEnd_at()
        );
    }

    public PlanBuilderResponse convertApiCallToResponse(PlanBuilderApiCall apicall) {
        return new PlanBuilderResponse(
                apicall.getCalendar().stream().map(this::convertCalendarApiCallToResponse).toList(),
                apicall.getTomatoes().stream().map(this::convertTomatoesApiCallToResponse).toList(),
                apicall.getRules().stream().map(this::convertRulesApiCallToResponse).toList()
        );
    }

    public PlanBuilderApiCall convertResponseToApiCall(PlanBuilderResponse response) {
        return new PlanBuilderApiCall(
                response.getCalendar().stream().map(this::convertCalendarResponseToApiCall).toList(),
                response.getTomatoes().stream().map(this::convertTomatoesResponseToApiCall).toList(),
                response.getRules().stream().map(this::convertRulesResponseToApiCall).toList(),
                null // config is not handled here
        );
    }

    public PlanBuilderResponse createPlanBuilder(PlanBuilderResponse planBuilderResponse) {
        String url = "/plans";
        PlanBuilderApiCall response = ApiCall.post(ApiCallUrl.BASE_URL, url, convertResponseToApiCall(planBuilderResponse), PlanBuilderApiCall.class);
        if (response == null) {
            return null;
        }
        return convertApiCallToResponse(response);
    }
}
