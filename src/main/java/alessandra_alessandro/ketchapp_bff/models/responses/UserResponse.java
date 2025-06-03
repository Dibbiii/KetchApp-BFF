package alessandra_alessandro.ketchapp_bff.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse { 
    
    private UUID uuid;
    
    private String username;
    
    private String email;
    
}
