package com.templateproject.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.templateproject.api.entity.Role;
import com.templateproject.api.entity.Users;
import com.templateproject.api.repository.RoleRepository;
import com.templateproject.api.repository.UsersRepository;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public UsersService(
            UsersRepository usersRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            @Lazy AuthenticationManager authManager,
            TokenService tokenService
    ) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Users getUser(Long id) { // Renamed 'getUsers' to 'getUser'
        return usersRepository.findById(id).orElse(null);
    }

    public Users register(String password, String email, String firstName) {
        String encodedPassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByAuthority("ROLE_users")
                .orElseThrow(() -> new RuntimeException("ROLE_users not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Users users = new Users(encodedPassword, email, firstName, roles);
        users.setUsersname(email);
        return usersRepository.save(users);
    }

    public Optional<Users> getUsersByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String login(String email, String password) {
        try {
            Authentication authentication = this.authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            return tokenService.generateToken(authentication);
        } catch (Exception e) {
            // Log the error or exception details here
            throw e; // Rethrow the exception if needed
        }
    }
}
