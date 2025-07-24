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

public class AchievementResponse {
    private Integer id;

    private UUID userUUID;

    private Integer AchievementNumber;

    private Timestamp createdAt;
}
