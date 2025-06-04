package alessandra_alessandro.ketchapp_bff.models.responses.PlanBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderTomatoesResponse {
    
    private String title;
    
    private String start_at;
    
    private String end_at;
    
    private String pause_end_at;
    
    private String subject;
    
}
