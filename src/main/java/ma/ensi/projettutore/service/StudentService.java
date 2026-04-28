package ma.ensi.projettutore.service;

import ma.ensi.projettutore.dto.response.UserResponse;
import ma.ensi.projettutore.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Integer id, Student student);
    void deleteStudent(Integer id);
    Student getStudentById(Integer id);
    Page<Student> getAllStudents(Pageable pageable);
    List<Student> getStudentsByQuizResults();
    String takeQuiz(Integer studentId, Integer quizId);
    String viewRecommendations(Integer studentId);
}