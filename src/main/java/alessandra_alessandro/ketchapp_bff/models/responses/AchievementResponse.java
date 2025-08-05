package alessandra_alessandro.ketchapp_bff.models.responses;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AchievementResponse {

    private Integer id;

    private UUID userUUID;

    private String description;

    private Timestamp createdAt;

    private boolean completed;
}
