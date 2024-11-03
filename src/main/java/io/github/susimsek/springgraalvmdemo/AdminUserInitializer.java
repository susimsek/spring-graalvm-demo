package io.github.susimsek.springgraalvmdemo;

import io.github.susimsek.springgraalvmdemo.entity.RoleEntity;
import io.github.susimsek.springgraalvmdemo.entity.UserEntity;
import io.github.susimsek.springgraalvmdemo.entity.UserRoleMappingEntity;
import io.github.susimsek.springgraalvmdemo.repository.RoleRepository;
import io.github.susimsek.springgraalvmdemo.repository.UserRepository;
import io.github.susimsek.springgraalvmdemo.repository.UserRoleMappingRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner initAdminUser(UserRepository userRepository,
                                           RoleRepository roleRepository,
                                           UserRoleMappingRepository userRoleMappingRepository,
                                           PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if the admin role already exists
            String adminRoleName = "ROLE_ADMIN";
            RoleEntity adminRole = roleRepository.findByName(adminRoleName).orElseGet(() -> {
                // Create a new admin role if it doesn't exist
                RoleEntity newRole = new RoleEntity();
                newRole.setId(UUID.randomUUID().toString());
                newRole.setName(adminRoleName);
                roleRepository.save(newRole);
                log.info("Admin role created with name: {}", adminRoleName);
                return newRole;
            });

            // Check if the admin user already exists
            String adminUsername = "admin";
            UserEntity adminUser = userRepository.findByUsername(adminUsername).orElseGet(() -> {
                // Create a new admin user if it doesn't exist
                UserEntity newUser = UserEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .username(adminUsername)
                    .password(passwordEncoder.encode("password"))
                    .email("admin@example.com")
                    .enabled(true)
                    .build();
                userRepository.save(newUser);
                log.info("Admin user created with username: {} and password: {}", adminUsername, "password");
                return newUser;
            });

            // Check if the user-role mapping already exists
            Optional<UserRoleMappingEntity> existingMapping = userRoleMappingRepository
                .findByUserIdAndRoleId(adminUser.getId(), adminRole.getId());
            if (existingMapping.isEmpty()) {
                // Create a new mapping if it doesn't exist
                UserRoleMappingEntity userRoleMapping = UserRoleMappingEntity.builder()
                    .userId(adminUser.getId())
                    .roleId(adminRole.getId())
                    .user(adminUser)
                    .role(adminRole)
                    .build();
                userRoleMappingRepository.save(userRoleMapping);
                log.info("Admin user assigned with role: {}", adminRoleName);
            }
        };
    }
}
