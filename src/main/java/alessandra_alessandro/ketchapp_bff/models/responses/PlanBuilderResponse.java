package alessandra_alessandro.ketchapp_bff.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderResponse {
    private List<PlanBuilderCalendarResponse> calendar;
    private List<PlanBuilderTomatoesResponse> tomatoes;
    private List<PlanBuilderRulesResponse> rules;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanBuilderCalendarResponse {
        private String title;
        private String start_at;
        private String end_at;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanBuilderTomatoesResponse {
        private String title;
        private String start_at;
        private String end_at;
        private String pause_end_at;
        private String subject;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanBuilderRulesResponse {
        private String title;
        private String start_at;
        private String end_at;
    }
}
