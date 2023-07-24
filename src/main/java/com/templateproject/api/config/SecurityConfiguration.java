package com.templateproject.api.config;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


@Configuration

@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${your.jwt.secret}")
    private String jwtSecret;
    @Bean
    public String jwtSecret() {
        return jwtSecret;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**", "/swagger-ui/**", "/v3/**", "/users/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/recipes/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/recipes/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/auth/**").authenticated();
                    auth.requestMatchers(HttpMethod.POST, "/users/**").authenticated();
                    auth.requestMatchers(HttpMethod.POST, "/ingredients-recipe").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/recipesallergens").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/recipesdiets").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/steps").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/recipes").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/{recipeSlug}").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/Slug").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/country").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/category").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/ingredients").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/Diet").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/Allergen").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/search").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/{recipeId}").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/ingredients").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/diets").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/countries").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/categories").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/allergens").permitAll();
                    auth.requestMatchers("/users/**").hasAuthority("SCOPE_ROLE_ADMIN");
                    auth.anyRequest().authenticated();
                })
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
        JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(key);
        return new NimbusJwtEncoder(immutableSecret);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey originalKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(originalKey).build();
    }

}