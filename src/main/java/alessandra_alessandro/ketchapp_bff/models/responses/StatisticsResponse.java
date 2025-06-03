package alessandra_alessandro.ketchapp_bff.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StatisticsResponse {
    private List<StatisticsDateResponse> dates;
}
