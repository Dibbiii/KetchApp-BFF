package alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderApiCall {
    
    private List<PlanBuilderCalendarApiCall> calendar;
    
    private List<PlanBuilderTomatoesApiCall> tomatoes;
    
    private List<PlanBuilderRulesApiCall> rules;
    
}
