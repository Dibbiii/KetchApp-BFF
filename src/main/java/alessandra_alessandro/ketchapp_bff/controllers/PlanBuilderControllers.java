package alessandra_alessandro.ketchapp_bff.controllers;

import alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder.PlanBuilderApiCall;
import alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder.PlanBuilderCalendarApiCall;
import alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder.PlanBuilderRulesApiCall;
import alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder.PlanBuilderTomatoesApiCall;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilder.PlanBuilderCalendarResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilder.PlanBuilderResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilder.PlanBuilderRulesResponse;
import alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilder.PlanBuilderTomatoesResponse;
import alessandra_alessandro.ketchapp_bff.utils.ApiCall;
import org.springframework.stereotype.Service;

@Service
public class PlanBuilderControllers {
    
    
    public PlanBuilderCalendarResponse convertCalendarApiCallToResponse(PlanBuilderCalendarApiCall apicall) {
        return new PlanBuilderCalendarResponse(
                apicall.getTitle(),
                apicall.getStart_at(),
                apicall.getEnd_at()
        );
    }
    
    public PlanBuilderCalendarApiCall convertCalendarResponseToApiCall(PlanBuilderCalendarResponse response) {
        return new PlanBuilderCalendarApiCall(
                response.getTitle(),
                response.getStart_at(),
                response.getEnd_at()
        );
    }
    
    public PlanBuilderTomatoesResponse convertTomatoesApiCallToResponse(PlanBuilderTomatoesApiCall apicall) {
        return new PlanBuilderTomatoesResponse(
                apicall.getTitle(),
                apicall.getStart_at(),
                apicall.getEnd_at(),
                apicall.getPause_end_at(),
                apicall.getSubject()
        );
    }
    
    public PlanBuilderTomatoesApiCall convertTomatoesResponseToApiCall(PlanBuilderTomatoesResponse response) {
        return new PlanBuilderTomatoesApiCall(
                response.getTitle(),
                response.getStart_at(),
                response.getEnd_at(),
                response.getPause_end_at(),
                response.getSubject()
        );
    }

    public PlanBuilderRulesResponse convertRulesApiCallToResponse(PlanBuilderRulesApiCall apicall) {
        return new PlanBuilderRulesResponse(
                apicall.getTitle(),
                apicall.getStart_at(),
                apicall.getEnd_at()
        );
    }

    public PlanBuilderRulesApiCall convertRulesResponseToApiCall(PlanBuilderRulesResponse response) {
        return new PlanBuilderRulesApiCall(
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
                response.getRules().stream().map(this::convertRulesResponseToApiCall).toList()
        );
    }

    public PlanBuilderResponse createPlanBuilder(PlanBuilderResponse planBuilderResponse) {
        String url = "/plans";
        PlanBuilderApiCall response = ApiCall.post(url, convertResponseToApiCall(planBuilderResponse), PlanBuilderApiCall.class);
        if (response == null) {
            return null;
        }
        return convertApiCallToResponse(response);
    }
}
