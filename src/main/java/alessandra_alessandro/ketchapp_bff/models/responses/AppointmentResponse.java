package alessandra_alessandro.ketchapp_bff.models.responses;

import jdk.jfr.Name;
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

public class AppointmentResponse {
    private String id;

    private UUID userUUID;

    private String name;

    private Timestamp startAt;

    private Timestamp endAt;

    private Timestamp createdAt;
}
