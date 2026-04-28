package ma.ensi.projettutore.service;

import ma.ensi.projettutore.dto.response.RecommendationResponse;
import ma.ensi.projettutore.entity.Recommendation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecommendationService {
    Recommendation createRecommendation(Recommendation recommendation);
    Recommendation updateRecommendation(Integer id, Recommendation recommendation);
    void deleteRecommendation(Integer id);
    Recommendation getRecommendationById(Integer id);
    Page<Recommendation> getAllRecommendations(Pageable pageable);
    List<Recommendation> getRecommendationsByUser(Integer userId);
    List<Recommendation> getRecommendationsBySchool(Integer schoolId);
    RecommendationResponse generateRecommendation(Integer userId, Integer schoolId);
}