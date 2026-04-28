package ma.ensi.projettutore.service;

import ma.ensi.projettutore.entity.CV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CVService {
    CV createCV(CV cv);
    CV updateCV(Integer id, CV cv);
    CV updateUserCV(Integer userId, CV cv);
    void deleteCV(Integer id);
    CV getCVById(Integer id);
    Page<CV> getAllCVs(Pageable pageable);
    CV getCVByUser(Integer userId);
    CV uploadCV(Integer userId, MultipartFile file);  // fixed: CVRequest → MultipartFile
    CV extractSkills(Integer id);                      // fixed: void → CV
    List<String> getUserSkills(Integer userId);        // added: was missing
}