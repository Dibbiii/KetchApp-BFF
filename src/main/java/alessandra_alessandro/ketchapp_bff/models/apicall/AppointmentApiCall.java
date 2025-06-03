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

public class AppointmentApiCall {
    private String id;

    private UUID userUUID;

    private String name;

    private Timestamp startAt;

    private Timestamp endAt;

    private Timestamp createdAt;
}
