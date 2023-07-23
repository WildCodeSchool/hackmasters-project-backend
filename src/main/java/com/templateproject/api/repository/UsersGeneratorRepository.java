package com.templateproject.api.repository;

import java.util.HashSet;
import java.util.Set;
import com.github.javafaker.Faker;
import com.templateproject.api.entity.Role;
import com.templateproject.api.entity.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsersGeneratorRepository implements CommandLineRunner {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Faker faker = new Faker();
    public UsersGeneratorRepository(UsersRepository usersRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(usersRepository.count() > 0) {
            return;
        }

        Role usersRole = new Role("ROLE_users");
        Role adminRole = new Role("ROLE_ADMIN");
        usersRole = roleRepository.save(usersRole);
        adminRole = roleRepository.save(adminRole);


        Set<Role> usersRoles = new HashSet<>();
        usersRoles.add(usersRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(usersRole);
        adminRoles.add(adminRole);

        Users users = new Users(
                passwordEncoder.encode("password"),
                faker.internet().emailAddress(),
                "users_first_name",
                usersRoles
        );
        Users admin = new Users(
                passwordEncoder.encode("password"),
                faker.internet().emailAddress(),
                "admin_first_name",
                adminRoles
        );
        usersRepository.save(users);
        usersRepository.save(admin);
    }

}