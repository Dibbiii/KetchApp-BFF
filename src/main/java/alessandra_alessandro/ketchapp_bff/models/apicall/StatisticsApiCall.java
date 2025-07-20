package alessandra_alessandro.ketchapp_bff.models.apicall;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsApiCall {
    private List<StatisticsDateApiCall> dates;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatisticsDateApiCall {
        private LocalDate date;
        private Double hours;
        private List<StatisticsSubjectApiCall> subjects;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatisticsSubjectApiCall {
        private String name;
        private Double hours;
    }
}
