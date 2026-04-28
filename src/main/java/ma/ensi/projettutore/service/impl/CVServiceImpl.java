package ma.ensi.projettutore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.CV;
import ma.ensi.projettutore.entity.Skill;
import ma.ensi.projettutore.entity.User;
import ma.ensi.projettutore.exception.ResourceNotFoundException;
import ma.ensi.projettutore.repository.CVRepository;
import ma.ensi.projettutore.repository.SkillRepository;
import ma.ensi.projettutore.repository.UserRepository;
import ma.ensi.projettutore.service.CVService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CVServiceImpl implements CVService {

    private final CVRepository cvRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    @Override
    public CV createCV(CV cv) {
        return cvRepository.save(cv);
    }

    @Override
    public CV updateCV(Integer id, CV cv) {
        CV existing = cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV not found with id: " + id));
        existing.setFilePath(cv.getFilePath());
        existing.setExtractedText(cv.getExtractedText());
        return cvRepository.save(existing);
    }

    @Override
    public CV updateUserCV(Integer userId, CV cv) {
        List<CV> cvs = cvRepository.findByUserId(userId);
        CV existing = cvs.stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("CV not found for user: " + userId));
        existing.setFilePath(cv.getFilePath());
        existing.setExtractedText(cv.getExtractedText());
        return cvRepository.save(existing);
    }

    @Override
    public void deleteCV(Integer id) {
        CV existing = cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV not found with id: " + id));
        cvRepository.delete(existing);
    }

    @Override
    public CV getCVById(Integer id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV not found with id: " + id));
    }

    @Override
    public Page<CV> getAllCVs(Pageable pageable) {
        return cvRepository.findAll(pageable);
    }

    @Override
    public CV getCVByUser(Integer userId) {
        return cvRepository.findByUserId(userId).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("CV not found for user: " + userId));
    }

    @Override
    public CV uploadCV(Integer userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        try {
            CV cv = new CV();
            cv.setUser(user);
            cv.setFilePath(file.getOriginalFilename());
            cv.setExtractedText("");
            return cvRepository.save(cv);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload CV: " + e.getMessage());
        }
    }

    @Override
    public CV extractSkills(Integer id) {
        CV cv = cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV not found with id: " + id));
        // Basic skill extraction from extracted text
        if (cv.getExtractedText() != null && !cv.getExtractedText().isEmpty()) {
            String[] words = cv.getExtractedText().split("\\s+");
            for (String word : words) {
                if (word.length() > 3) {
                    Skill skill = skillRepository.findByName(word)
                            .orElseGet(() -> skillRepository.save(
                                    Skill.builder().name(word).build()
                            ));
                    cv.getSkills().add(skill);
                }
            }
        }
        return cvRepository.save(cv);
    }

    @Override
    public List<String> getUserSkills(Integer userId) {
        return cvRepository.findByUserId(userId).stream()
                .flatMap(cv -> cv.getSkills().stream())
                .map(Skill::getName)
                .distinct()
                .collect(Collectors.toList());
    }
}