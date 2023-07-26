package com.templateproject.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.templateproject.api.entity.Role;
import com.templateproject.api.entity.Users;
import com.templateproject.api.repository.RoleRepository;
import com.templateproject.api.repository.UsersRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender; // Ajoutez cette ligne d'importation
import java.util.Optional;


@Service
@Transactional
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final JavaMailSender emailSender;

    public UsersService(
            UsersRepository usersRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            @Lazy AuthenticationManager authManager,
            TokenService tokenService,
            JavaMailSender emailSender
    ) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.emailSender = emailSender;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Users getUser(Long id) { // Renamed 'getUsers' to 'getUser'
        return usersRepository.findById(id).orElse(null);
    }

    public Users register(String email, String password, String firstname) {
        Optional<Users> existingUserOptional = usersRepository.findByEmail(email);
        if (existingUserOptional.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        Set<Role> roles = new HashSet<>(); // You may need to initialize roles based on your application logic
        Users newUser = new Users(password, email, firstname, roles);
        return usersRepository.save(newUser);
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

    public void sendPasswordResetEmail(String userEmail) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            String resetToken = "test";
            Users user = usersRepository.findByEmail(userEmail).get();
            user.setResetToken(resetToken);
            usersRepository.save(user);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userEmail);
            helper.setSubject("Password Reset");

            String emailContent = "<h3>Hello,</h3>"
                    + "<p>You have requested a password reset. Click the link below to create a new password:</p>"
                    + "<a href='http://localhost:4200/reset-password?email=" + userEmail + "&resetToken="+ resetToken + "'>Create a new password</a>"
                    + "<p>If you did not make this requhostest, you can ignore this email.</p>";
            helper.setText(emailContent, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            // Handle email sending errors here
            e.printStackTrace();
        }
    }

    public Users resetPassword(String email, String resetToken, String password) {
        Users user = usersRepository.findByEmail(email).get();
        if (resetToken != null) {
            user.setPassword(passwordEncoder.encode(password));
            user.setResetToken(null);
            usersRepository.save(user);
        }
        return user;
    }
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElse(null);
    }
}
