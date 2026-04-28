package ma.ensi.projettutore.service;

import ma.ensi.projettutore.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SchoolService {
    School createSchool(School school);
    School updateSchool(Integer id, School school);
    void deleteSchool(Integer id);
    School getSchoolById(Integer id);
    Page<School> getAllSchools(Pageable pageable);
    List<School> getSchoolsByDomain(String domain);
    School addSchool(School school);         // fixed: String → School
    School updateSchoolInfo(Integer schoolId, School school);  // fixed: String → School
}