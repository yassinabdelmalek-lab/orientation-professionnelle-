package ma.ensi.projettutore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CVRequest {
    @NotBlank(message = "File path is required")
    private String filePath;

    private String extractedText;
}