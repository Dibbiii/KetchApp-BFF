package alessandra_alessandro.ketchapp_bff.models.apicall;

import alessandra_alessandro.ketchapp_bff.models.enums.ActivityAction;
import alessandra_alessandro.ketchapp_bff.models.enums.ActivityType;
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

public class ActivityApiCall {
    private Integer id;

    private UUID userUUID;

    private Integer tomatoId;

    private ActivityType type;

    private ActivityAction action;

    private Timestamp createdAt;
}
