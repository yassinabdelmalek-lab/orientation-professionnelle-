package ma.ensi.projettutore.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.Admin;
import ma.ensi.projettutore.entity.enums.Role;
import ma.ensi.projettutore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        if (!userRepository.existsByEmail("admin@ensi.tn")) {

            Admin admin = new Admin();

            admin.setName("Super Admin");
            admin.setEmail("admin@ensi.tn");

            admin.setPassword(
                    passwordEncoder.encode("Admin123456")
            );

            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("Admin account created");
        }
    }
}