package ma.ensi.projettutore.service;

import ma.ensi.projettutore.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Integer id, Employee employee);
    void deleteEmployee(Integer id);
    Employee getEmployeeById(Integer id);
    Page<Employee> getAllEmployees(Pageable pageable);
    String viewRecommendations(Integer employeeId);
}