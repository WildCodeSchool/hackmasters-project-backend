package com.templateproject.api.repository;

import java.util.HashSet;
import java.util.Set;

import com.github.javafaker.Faker;
import com.templateproject.api.entity.Role;
import com.templateproject.api.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserGeneratorRepository implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Faker faker = new Faker();
    public UserGeneratorRepository(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() > 0) {
            return;
        }

        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        userRole = roleRepository.save(userRole);
        adminRole = roleRepository.save(adminRole);


        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(userRole);
        adminRoles.add(adminRole);

        User user = new User(
                passwordEncoder.encode("password"),
                faker.internet().emailAddress(),
                "user_first_name", // Replace this with an actual first name or leave it as is
                userRoles
        );
        User admin = new User(
                passwordEncoder.encode("password"),
                faker.internet().emailAddress(),
                "admin_first_name", // Replace this with an actual first name or leave it as is
                adminRoles
        );
        userRepository.save(user);
        userRepository.save(admin);
    }

}