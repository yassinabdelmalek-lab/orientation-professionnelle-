package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.School;
import ma.ensi.projettutore.service.SchoolService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
@CrossOrigin
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<School> createSchool(@Valid @RequestBody School school) {
        return ResponseEntity.ok(schoolService.createSchool(school));
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Integer id, @Valid @RequestBody School school) {
        return ResponseEntity.ok(schoolService.updateSchool(id, school));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Integer id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.getSchoolById(id));
    }

    @GetMapping
    public ResponseEntity<Page<School>> getAllSchools(Pageable pageable) {
        return ResponseEntity.ok(schoolService.getAllSchools(pageable));
    }

    @GetMapping("/domain/{domain}")
    public ResponseEntity<List<School>> getSchoolsByDomain(@PathVariable String domain) {
        return ResponseEntity.ok(schoolService.getSchoolsByDomain(domain));
    }

    @PostMapping("/add")
    public ResponseEntity<School> addSchool(@Valid @RequestBody School school) {
        return ResponseEntity.ok(schoolService.addSchool(school));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<School> updateSchoolDetails(@PathVariable Integer id, @Valid @RequestBody School school) {
        return ResponseEntity.ok(schoolService.updateSchool(id, school));
    }
}