package alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderResponse {
    
    private List<PlanBuilderCalendarResponse> calendar;
    
    private List<PlanBuilderTomatoesResponse> tomatoes;
    
    private List<PlanBuilderRulesResponse> rules;
    
}
