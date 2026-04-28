package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.Admin;
import ma.ensi.projettutore.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @Valid @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.updateAdmin(id, admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Admin>> getAllAdmins(Pageable pageable) {
        return ResponseEntity.ok(adminService.getAllAdmins(pageable));
    }

    @PostMapping("/manage-users")
    public ResponseEntity<Void> manageUsers() {
        adminService.manageUsers();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-offer")
    public ResponseEntity<Void> addOffer() {
        adminService.addOffer();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-offer")
    public ResponseEntity<Void> updateOffer() {
        adminService.updateOffer();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-offer")
    public ResponseEntity<Void> deleteOffer() {
        adminService.deleteOffer();
        return ResponseEntity.ok().build();
    }
}