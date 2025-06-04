package alessandra_alessandro.ketchapp_bff.models.apicall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupApiCall {
    
    private Integer id;
    
    private Timestamp createdAt;
    
}
