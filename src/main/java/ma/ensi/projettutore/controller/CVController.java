package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.request.CVRequest;
import ma.ensi.projettutore.entity.CV;
import ma.ensi.projettutore.service.CVService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cvs")
@RequiredArgsConstructor
@CrossOrigin
public class CVController {

    private final CVService cvService;

    @PostMapping
    public ResponseEntity<CV> createCV(@Valid @RequestBody CV cv) {
        return ResponseEntity.ok(cvService.createCV(cv));
    }

    @PostMapping("/upload")
    public ResponseEntity<CV> uploadCV(@RequestParam Integer userId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(cvService.uploadCV(userId, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CV> updateCV(@PathVariable Integer id, @Valid @RequestBody CV cv) {
        return ResponseEntity.ok(cvService.updateCV(id, cv));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<CV> updateUserCV(@PathVariable Integer userId, @Valid @RequestBody CVRequest request) {
        CV cv = new CV();
        cv.setFilePath(request.getFilePath());
        cv.setExtractedText(request.getExtractedText());
        return ResponseEntity.ok(cvService.updateUserCV(userId, cv));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCV(@PathVariable Integer id) {
        cvService.deleteCV(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CV> getCVById(@PathVariable Integer id) {
        return ResponseEntity.ok(cvService.getCVById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CV>> getAllCVs(Pageable pageable) {
        return ResponseEntity.ok(cvService.getAllCVs(pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CV> getCVByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(cvService.getCVByUser(userId));
    }

    @PostMapping("/{id}/extract")
    public ResponseEntity<CV> extractSkills(@PathVariable Integer id) {
        return ResponseEntity.ok(cvService.extractSkills(id));
    }

    @GetMapping("/user/{userId}/skills")
    public ResponseEntity<List<String>> getUserSkills(@PathVariable Integer userId) {
        return ResponseEntity.ok(cvService.getUserSkills(userId));
    }
}