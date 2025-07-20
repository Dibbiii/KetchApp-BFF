package alessandra_alessandro.ketchapp_bff.models.apicall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBuilderApiCall {
    private List<PlanBuilderCalendarApiCall> calendar;
    private List<PlanBuilderTomatoesApiCall> tomatoes;
    private List<PlanBuilderRulesApiCall> rules;
    private PlanBuilderConfigApiCall config;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanBuilderCalendarApiCall {
        private String title;
        private String start_at;
        private String end_at;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanBuilderTomatoesApiCall {
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
    public static class PlanBuilderRulesApiCall {
        private String title;
        private String start_at;
        private String end_at;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanBuilderConfigApiCall {
        private PlanBuilderNotificationsApiCall notifications;
        private String session;
        private String pause;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanBuilderNotificationsApiCall {
        private boolean enabled;
        private String sound;
        private boolean vibration;
    }
}
