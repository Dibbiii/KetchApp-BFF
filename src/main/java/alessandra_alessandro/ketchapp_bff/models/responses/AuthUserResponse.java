package alessandra_alessandro.ketchapp_bff.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserResponse {

    private UUID id;
    private String email;
    private String username;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    private String token;
}
