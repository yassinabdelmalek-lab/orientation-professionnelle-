package ma.ensi.projettutore.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@Entity
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    public Student() { super(); }

    public String takeQuiz() {
        return "Quiz taken";
    }

    public void uploadCV() {}

    public String viewRecommendations() {
        return "Recommendations viewed";
    }
}