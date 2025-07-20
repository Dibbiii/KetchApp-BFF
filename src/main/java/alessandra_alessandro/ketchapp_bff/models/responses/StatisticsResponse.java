package alessandra_alessandro.ketchapp_bff.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {
    private List<StatisticsDateResponse> dates;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatisticsDateResponse {
        private LocalDate date;
        private Double hours;
        private List<StatisticsSubjectResponse> subjects;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatisticsSubjectResponse {
        private String name;
        private Double hours;
    }
}
