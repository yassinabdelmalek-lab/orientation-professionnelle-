package ma.ensi.projettutore.service;

import ma.ensi.projettutore.dto.response.QuizResultResponse;
import ma.ensi.projettutore.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    Quiz updateQuiz(Integer id, Quiz quiz);
    void deleteQuiz(Integer id);
    Quiz getQuizById(Integer id);
    Page<Quiz> getAllQuizzes(Pageable pageable);
    List<Quiz> getQuizzesByUser(Integer userId);
    QuizResultResponse calculateResult(Integer quizId);
    void startQuiz(Integer quizId);
}