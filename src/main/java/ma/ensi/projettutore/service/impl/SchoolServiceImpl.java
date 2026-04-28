package ma.ensi.projettutore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.School;
import ma.ensi.projettutore.exception.ResourceNotFoundException;
import ma.ensi.projettutore.repository.SchoolRepository;
import ma.ensi.projettutore.service.SchoolService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School updateSchool(Integer id, School school) {
        School existing = schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + id));
        existing.setName(school.getName());
        existing.setDescription(school.getDescription());
        existing.setDomain(school.getDomain());
        return schoolRepository.save(existing);
    }

    @Override
    public void deleteSchool(Integer id) {
        School existing = schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + id));
        schoolRepository.delete(existing);
    }

    @Override
    public School getSchoolById(Integer id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School not found with id: " + id));
    }

    @Override
    public Page<School> getAllSchools(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

    @Override
    public List<School> getSchoolsByDomain(String domain) {
        return schoolRepository.findAll().stream()
                .filter(s -> domain.equalsIgnoreCase(s.getDomain()))
                .toList();
    }

    @Override
    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School updateSchoolInfo(Integer schoolId, School school) {
        return updateSchool(schoolId, school);
    }
}