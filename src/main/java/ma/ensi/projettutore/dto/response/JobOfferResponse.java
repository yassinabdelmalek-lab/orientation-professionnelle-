package ma.ensi.projettutore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOfferResponse {
    private int id;
    private String titre;
    private String description;
    private String category;
    private String userName;
}