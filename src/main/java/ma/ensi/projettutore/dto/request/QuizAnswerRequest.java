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
public class QuizAnswerRequest {
    @NotBlank(message = "Quiz ID is required")
    private String quizId;

    @NotBlank(message = "Answer ID is required")
    private String answerId;
}