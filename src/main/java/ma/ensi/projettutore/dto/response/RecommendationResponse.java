package ma.ensi.projettutore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationResponse {
    private int id;
    private int score;
    private String type;
    private String userName;
    private String schoolName;
}