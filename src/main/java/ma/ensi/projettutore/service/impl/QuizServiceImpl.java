package ma.ensi.projettutore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.response.QuizResultResponse;
import ma.ensi.projettutore.entity.Quiz;
import ma.ensi.projettutore.exception.ResourceNotFoundException;
import ma.ensi.projettutore.repository.QuizRepository;
import ma.ensi.projettutore.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Integer id, Quiz quiz) {
        Quiz existing = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
        existing.setResult(quiz.getResult());
        return quizRepository.save(existing);
    }

    @Override
    public void deleteQuiz(Integer id) {
        Quiz existing = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
        quizRepository.delete(existing);
    }

    @Override
    public Quiz getQuizById(Integer id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }

    @Override
    public Page<Quiz> getAllQuizzes(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public List<Quiz> getQuizzesByUser(Integer userId) {
        return quizRepository.findByUserId(userId);
    }

    @Override
    public QuizResultResponse calculateResult(Integer quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
        return QuizResultResponse.builder()
                .quizId(quiz.getId())
                .result(quiz.getResult())
                .feedback("Quiz completed successfully")
                .build();
    }

    @Override
    public void startQuiz(Integer quizId) {
        quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
    }
}