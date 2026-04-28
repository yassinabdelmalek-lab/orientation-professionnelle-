package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.response.RecommendationResponse;
import ma.ensi.projettutore.entity.Recommendation;
import ma.ensi.projettutore.service.RecommendationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@CrossOrigin
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<Recommendation> createRecommendation(@Valid @RequestBody Recommendation recommendation) {
        return ResponseEntity.ok(recommendationService.createRecommendation(recommendation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recommendation> updateRecommendation(@PathVariable Integer id, @Valid @RequestBody Recommendation recommendation) {
        return ResponseEntity.ok(recommendationService.updateRecommendation(id, recommendation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Integer id) {
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> getRecommendationById(@PathVariable Integer id) {
        return ResponseEntity.ok(recommendationService.getRecommendationById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Recommendation>> getAllRecommendations(Pageable pageable) {
        return ResponseEntity.ok(recommendationService.getAllRecommendations(pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByUser(userId));
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsBySchool(@PathVariable Integer schoolId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsBySchool(schoolId));
    }

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation(@RequestParam Integer userId, @RequestParam Integer schoolId) {
        return ResponseEntity.ok(recommendationService.generateRecommendation(userId, schoolId));
    }
}