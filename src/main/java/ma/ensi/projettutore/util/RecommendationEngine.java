package ma.ensi.projettutore.util;

import ma.ensi.projettutore.entity.Profile;
import ma.ensi.projettutore.entity.Recommendation;
import ma.ensi.projettutore.entity.School;
import ma.ensi.projettutore.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RecommendationEngine {

    private static final double MINIMUM_MATCH_SCORE = 0.3;
    private static final String[] RECOMMENDATION_TYPES = {
        Constants.RECOMMENDATION_TYPE_ACADEMIC,
        Constants.RECOMMENDATION_TYPE_PROFESSIONAL,
        Constants.RECOMMENDATION_TYPE_PERSONAL
    };

    public Recommendation generateRecommendation(User user, School school) {
        Profile profile = user.getProfile();
        if (profile == null) {
            return createDefaultRecommendation(user, school);
        }

        double matchScore = calculateMatchScore(profile, school);
        
        if (matchScore < MINIMUM_MATCH_SCORE) {
            return createLowMatchRecommendation(user, school, matchScore);
        }

        return createHighMatchRecommendation(user, school, matchScore);
    }

    private double calculateMatchScore(Profile profile, School school) {
        double score = 0.0;
        int totalFactors = 0;

        // Check domain match
        if (profile.getEducation() != null && school.getDomain() != null) {
            totalFactors++;
            if (isDomainMatch(profile.getEducation(), school.getDomain())) {
                score += 0.4;
            }
        }

        // Check experience match
        if (profile.getExperience() != null && school.getDomain() != null) {
            totalFactors++;
            if (isExperienceRelevant(profile.getExperience(), school.getDomain())) {
                score += 0.3;
            }
        }

        // Check interests match
        if (profile.getInterests() != null && school.getDomain() != null) {
            totalFactors++;
            if (isInterestMatch(profile.getInterests(), school.getDomain())) {
                score += 0.2;
            }
        }

        // Check preferred work match
        if (profile.getPreferred() != null) {
            totalFactors++;
            if (isWorkPreferenceMatch(profile.getPreferred())) {
                score += 0.1;
            }
        }

        return totalFactors > 0 ? score / totalFactors : 0.0;
    }

    private boolean isDomainMatch(String education, String schoolDomain) {
        if (education == null || schoolDomain == null) return false;
        
        String educationLower = education.toLowerCase();
        String domainLower = schoolDomain.toLowerCase();
        
        return educationLower.contains(domainLower) || 
               domainLower.contains(educationLower) ||
               hasRelatedField(educationLower, domainLower);
    }

    private boolean hasRelatedField(String education, String domain) {
        // Check for related fields
        if ((education.contains("computer") || education.contains("software")) && 
            domain.contains("computer science")) {
            return true;
        }
        if ((education.contains("mechanical") || education.contains("electrical")) && 
            domain.contains("engineering")) {
            return true;
        }
        if ((education.contains("business") || education.contains("management")) && 
            domain.contains("business")) {
            return true;
        }
        if ((education.contains("medical") || education.contains("health")) && 
            domain.contains("medicine")) {
            return true;
        }
        return false;
    }

    private boolean isExperienceRelevant(String experience, String schoolDomain) {
        if (experience == null || schoolDomain == null) return false;
        
        String expLower = experience.toLowerCase();
        String domainLower = schoolDomain.toLowerCase();
        
        return expLower.contains(domainLower) ||
               (expLower.contains("internship") && domainLower.contains("computer")) ||
               (expLower.contains("research") && domainLower.contains("science")) ||
               (expLower.contains("project") && domainLower.contains("engineering"));
    }

    private boolean isInterestMatch(String interests, String schoolDomain) {
        if (interests == null || schoolDomain == null) return false;
        
        String interestLower = interests.toLowerCase();
        String domainLower = schoolDomain.toLowerCase();
        
        return interestLower.contains(domainLower) ||
               (interestLower.contains("technology") && domainLower.contains("computer")) ||
               (interestLower.contains("innovation") && domainLower.contains("engineering")) ||
               (interestLower.contains("finance") && domainLower.contains("business"));
    }

    private boolean isWorkPreferenceMatch(String preferred) {
        if (preferred == null) return false;
        
        String prefLower = preferred.toLowerCase();
        return prefLower.contains("remote") || 
               prefLower.contains("hybrid") || 
               prefLower.contains("onsite");
    }

    private Recommendation createHighMatchRecommendation(User user, School school, double matchScore) {
        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setSchool(school);
        recommendation.setScore((int) (matchScore * 100));
        recommendation.setType(getRandomRecommendationType());
        
        String description = String.format(
            "Strong match (%d%%) between your profile and %s. " +
            "Based on your %s background and interests in %s, " +
            "this school appears to be an excellent fit for your career goals.",
            recommendation.getScore(),
            school.getName(),
            user.getProfile() != null ? user.getProfile().getEducation() : "academic",
            user.getProfile() != null ? user.getProfile().getInterests() : "various fields"
        );
        
        return recommendation;
    }

    private Recommendation createLowMatchRecommendation(User user, School school, double matchScore) {
        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setSchool(school);
        recommendation.setScore((int) (matchScore * 100));
        recommendation.setType(Constants.RECOMMENDATION_TYPE_PERSONAL);
        
        String description = String.format(
            "Limited match (%d%%) detected. While there may not be a strong " +
            "alignment with your current profile, this school could offer " +
            "valuable opportunities for growth and new experiences.",
            recommendation.getScore()
        );
        
        return recommendation;
    }

    private Recommendation createDefaultRecommendation(User user, School school) {
        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setSchool(school);
        recommendation.setScore(50);
        recommendation.setType(Constants.RECOMMENDATION_TYPE_ACADEMIC);
        
        String description = String.format(
            "Basic recommendation for %s. Consider exploring this school " +
            "further to determine if it aligns with your educational and " +
            "career objectives.",
            school.getName()
        );
        
        return recommendation;
    }

    private String getRandomRecommendationType() {
        Random random = new Random();
        return RECOMMENDATION_TYPES[random.nextInt(RECOMMENDATION_TYPES.length)];
    }

    public List<Recommendation> generateMultipleRecommendations(User user, List<School> schools) {
        List<Recommendation> recommendations = new ArrayList<>();
        
        for (School school : schools) {
            Recommendation recommendation = generateRecommendation(user, school);
            if (recommendation.getScore() >= MINIMUM_MATCH_SCORE * 100) {
                recommendations.add(recommendation);
            }
        }
        
        // Sort by score descending
        recommendations.sort((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()));
        
        return recommendations;
    }

    public boolean isEligibleForRecommendation(User user) {
        if (user == null) return false;
        
        Profile profile = user.getProfile();
        if (profile == null) return true; // Allow recommendations even without profile
        
        // Check if profile has minimum required information
        boolean hasEducation = profile.getEducation() != null && !profile.getEducation().trim().isEmpty();
        boolean hasExperience = profile.getExperience() != null && !profile.getExperience().trim().isEmpty();
        boolean hasInterests = profile.getInterests() != null && !profile.getInterests().trim().isEmpty();
        
        return hasEducation || hasExperience || hasInterests;
    }
}