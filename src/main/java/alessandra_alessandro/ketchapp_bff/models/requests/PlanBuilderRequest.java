package alessandra_alessandro.ketchapp_bff.models.requests;

import lombok.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderRequest {
    private UUID userID;

    private String session;

    private String breakDuration;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Calendar {
        private String start_at;
        private String end_at;
        private String title;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Subject {
        private String name;
        private String duration;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tomato {
        private String start_at;
        private String end_at;
        private String pause_end_at;
    }

    private List<Calendar> calendar;

    @Valid
    @NotNull(message = "{planbuilder.subjects.notnull}")
    @Size(min = 1, message = "{planbuilder.subjects.size}")
    private List<Subject> subjects;

}
