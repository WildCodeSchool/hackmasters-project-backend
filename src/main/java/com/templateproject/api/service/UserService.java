package com.templateproject.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.templateproject.api.entity.Role;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.RoleRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            @Lazy AuthenticationManager authManager,
            TokenService tokenService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.tokenService = tokenService;

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public User register(String password, String email, String firstName) {
        String encodedPassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByAuthority("ROLE_USER").orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User(encodedPassword, email, firstName, roles);
        user.setUsername(email);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String login(String email, String password) {
        Authentication authentication = this.authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return tokenService.generateToken(authentication);
    }
}
