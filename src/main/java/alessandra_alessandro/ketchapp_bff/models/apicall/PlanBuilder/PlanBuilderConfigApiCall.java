package alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderConfigApiCall {
    
    private PlanBuilderNotificationsApiCall notifications;
    
    private String session;
    
    private String pause;
    
}
