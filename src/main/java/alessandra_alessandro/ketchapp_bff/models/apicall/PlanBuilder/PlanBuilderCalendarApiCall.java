package alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderCalendarApiCall {
    
    private String title;
    
    private String start_at;
    
    private String end_at;
    
}
