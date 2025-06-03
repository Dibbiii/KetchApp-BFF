package alessandra_alessandro.ketchapp_bff.models.apicall;

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

public class TomatoApiCall {
    private Integer id;

    private UUID userUUID;

    private Integer groupId;

    private Timestamp startAt;

    private Timestamp endAt;

    private Timestamp pauseEnd;

    private Integer nextTomatoId;

    private String subject;

    private Timestamp createdAt;
}
