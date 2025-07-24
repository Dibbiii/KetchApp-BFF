package alessandra_alessandro.ketchapp_bff.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String uuid;
    private String username;
    private Integer totalHours;
}
