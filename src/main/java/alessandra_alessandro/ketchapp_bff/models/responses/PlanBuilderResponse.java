package alessandra_alessandro.ketchapp_bff.models.responses;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderResponse {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Calendar {
        private String title;
        private String start_at;
        private String end_at;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tomato {
        private String start_at;
        private String end_at;
        private String pause_end_at;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Subject {
        private String name;
        private List<Tomato> tomatoes;
    }

    private List<Calendar> calendar;
    private List<Subject> subjects;

}