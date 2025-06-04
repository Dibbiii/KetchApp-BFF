package alessandra_alessandro.ketchapp_bff.models.apicall.PlanBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderNotificationsApiCall {
    
    private boolean enabled;
    
    private String sound;
    
    private boolean vibration;
    
}
