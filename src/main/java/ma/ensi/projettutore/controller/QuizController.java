package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.request.QuizAnswerRequest;
import ma.ensi.projettutore.dto.response.QuizResultResponse;
import ma.ensi.projettutore.entity.Quiz;
import ma.ensi.projettutore.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer id, @Valid @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.updateQuiz(id, quiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Quiz>> getAllQuizzes(Pageable pageable) {
        return ResponseEntity.ok(quizService.getAllQuizzes(pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Quiz>> getQuizzesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(quizService.getQuizzesByUser(userId));
    }

    @PostMapping("/{quizId}/calculate-result")
    public ResponseEntity<QuizResultResponse> calculateResult(@PathVariable Integer quizId) {
        return ResponseEntity.ok(quizService.calculateResult(quizId));
    }

    @PostMapping("/{quizId}/start")
    public ResponseEntity<Void> startQuiz(@PathVariable Integer quizId) {
        quizService.startQuiz(quizId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/answer")
    public ResponseEntity<QuizResultResponse> submitAnswer(@Valid @RequestBody QuizAnswerRequest request) {
        // This would typically process the answer and calculate results
        QuizResultResponse result = new QuizResultResponse();
        result.setQuizId(Integer.parseInt(request.getQuizId()));
        result.setResult("Answer submitted");
        result.setFeedback("Thank you for your submission");
        return ResponseEntity.ok(result);
    }
}