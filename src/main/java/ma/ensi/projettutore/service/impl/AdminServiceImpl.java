package ma.ensi.projettutore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.Admin;
import ma.ensi.projettutore.exception.ResourceNotFoundException;
import ma.ensi.projettutore.repository.AdminRepository;
import ma.ensi.projettutore.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Integer id, Admin admin) {
        Admin existing = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
        existing.setName(admin.getName());
        existing.setEmail(admin.getEmail());
        existing.setRole(admin.getRole());
        return adminRepository.save(existing);
    }

    @Override
    public void deleteAdmin(Integer id) {
        Admin existing = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
        adminRepository.delete(existing);
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
    }

    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public void manageUsers() {
        // Admin user management logic
    }

    @Override
    public void addOffer() {
        // Admin add offer logic
    }

    @Override
    public void updateOffer() {
        // Admin update offer logic
    }

    @Override
    public void deleteOffer() {
        // Admin delete offer logic
    }
}