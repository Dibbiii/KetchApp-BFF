package alessandra_alessandro.ketchapp_bff.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private UUID id;
    private String username;
    private String email;
}

