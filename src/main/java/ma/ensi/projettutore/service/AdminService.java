package ma.ensi.projettutore.service;

import ma.ensi.projettutore.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin updateAdmin(Integer id, Admin admin);
    void deleteAdmin(Integer id);
    Admin getAdminById(Integer id);
    Page<Admin> getAllAdmins(Pageable pageable);
    void manageUsers();
    void addOffer();
    void updateOffer();
    void deleteOffer();
}