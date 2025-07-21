package alessandra_alessandro.ketchapp_bff.models.responses;

import alessandra_alessandro.ketchapp_bff.models.enums.ActivityAction;
import alessandra_alessandro.ketchapp_bff.models.enums.ActivityType;
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
public class ActivityResponse {

    private Integer id;

    private UUID userUUID;

    private Integer tomatoId;

    private ActivityType type;

    private ActivityAction action;

    private Timestamp createdAt;
}
