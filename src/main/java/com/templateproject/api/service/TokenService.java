package com.templateproject.api.service;

import java.time.Instant;
import java.util.Base64;
import java.util.stream.Collectors;
import com.templateproject.api.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final byte[] jwtSecret;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtSecret = Base64.getDecoder().decode("VzyVUCJujcNQi0iK8uSEVJc1MdjDkBx/E5ru+I0KoD8=");
    }

    public String generateToken(Authentication auth) {
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(30 * 24 * 60 * 60);
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(auth.getName())
                .claim("scope", scope)
                .claim("username", ((User) auth.getPrincipal()).getUserStringName())
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

    }

}