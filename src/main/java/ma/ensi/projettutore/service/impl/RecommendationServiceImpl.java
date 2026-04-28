package ma.ensi.projettutore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.response.RecommendationResponse;
import ma.ensi.projettutore.entity.Recommendation;
import ma.ensi.projettutore.entity.School;
import ma.ensi.projettutore.entity.User;
import ma.ensi.projettutore.exception.ResourceNotFoundException;
import ma.ensi.projettutore.repository.RecommendationRepository;
import ma.ensi.projettutore.repository.SchoolRepository;
import ma.ensi.projettutore.repository.UserRepository;
import ma.ensi.projettutore.service.RecommendationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public Recommendation createRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation updateRecommendation(Integer id, Recommendation recommendation) {
        Recommendation existing = recommendationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with id: " + id));
        existing.setScore(recommendation.getScore());
        existing.setType(recommendation.getType());
        return recommendationRepository.save(existing);
    }

    @Override
    public void deleteRecommendation(Integer id) {
        Recommendation existing = recommendationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with id: " + id));
        recommendationRepository.delete(existing);
    }

    @Override
    public Recommendation getRecommendationById(Integer id) {
        return recommendationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with id: " + id));
    }

    @Override
    public Page<Recommendation> getAllRecommendations(Pageable pageable) {
        return recommendationRepository.findAll(pageable);
    }

    @Override
    public List<Recommendation> getRecommendationsByUser(Integer userId) {
        return recommendationRepository.findByUserId(userId);
    }

    @Override
    public List<Recommendation> getRecommendationsBySchool(Integer schoolId) {
        return recommendationRepository.findBySchoolId(schoolId);
    }

    @Override
    public RecommendationResponse generateRecommendation(Integer userId, Integer schoolId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + schoolId));
        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setSchool(school);
        recommendation.setScore(0);
        recommendation.setType("AUTO");
        Recommendation saved = recommendationRepository.save(recommendation);
        return RecommendationResponse.builder()
                .id(saved.getId())
                .score(saved.getScore())
                .type(saved.getType())
                .userName(user.getName())
                .schoolName(school.getName())
                .build();
    }
}