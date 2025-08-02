package alessandra_alessandro.ketchapp_bff.models.responses;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    public UserResponse(UUID id) {
        this.id = id;
    }

    private UUID id;
    private String username;
    private Integer totalHours;
}
