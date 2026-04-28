package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.response.UserResponse;
import ma.ensi.projettutore.entity.Student;
import ma.ensi.projettutore.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @GetMapping("/{studentId}/quiz-results")
    public ResponseEntity<List<Student>> getStudentsByQuizResults() {
        return ResponseEntity.ok(studentService.getStudentsByQuizResults());
    }

    @PostMapping("/{studentId}/quiz/{quizId}/take")
    public ResponseEntity<String> takeQuiz(@PathVariable Integer studentId, @PathVariable Integer quizId) {
        return ResponseEntity.ok(studentService.takeQuiz(studentId, quizId));
    }

    @GetMapping("/{studentId}/recommendations")
    public ResponseEntity<String> viewRecommendations(@PathVariable Integer studentId) {
        return ResponseEntity.ok(studentService.viewRecommendations(studentId));
    }
}