package ma.ensi.projettutore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToMany(mappedBy = "questions")
    private Set<Quiz> quizzes;

    @ManyToMany
    @JoinTable(
        name = "question_answers",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    private Set<Answer> answers;

    public String displayQuestion() {
        return text;
    }
}