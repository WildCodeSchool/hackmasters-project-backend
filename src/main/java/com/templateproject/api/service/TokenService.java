//package com.templateproject.api.service;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//import java.util.stream.Collectors;
//
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TokenService {
//
//    private final String jwtSecret; // Your secret key (Base64-encoded string)
//
//    @Autowired
//    public TokenService(String jwtSecret) {
//        this.jwtSecret = jwtSecret;
//    }
//    public String generateToken(Authentication auth) {
//        try {
//            Instant now = Instant.now();
//            Instant expiration = now.plus(30, ChronoUnit.DAYS);
//
//            String scope = auth.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.joining(" "));
//
//            Jwt jwt = Jwt.withTokenValue("dummy") // Set any dummy value
//                    .header("alg", "HS256") // Algorithm used for signing
//                    .claim("iss", "self") // Issuer
//                    .claim("sub", auth.getName()) // Subject (username)
//                    .claim("scope", scope) // Scopes/Authorities
//                    .issuedAt(Date.from(now).toInstant()) // Convert Instant to Date
//                    .expiresAt(Date.from(expiration).toInstant()) // Convert Instant to Date
//                    .build();
//
//            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).build();
//            JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jwt.getClaims()));
//            JWSSigner signer = new MACSigner(jwtSecret.getBytes());
//            jwsObject.sign(signer);
//
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            // Gérer l'exception ici (par exemple, journaliser l'erreur, renvoyer une valeur par défaut, etc.)
//            e.printStackTrace(); // Vous pouvez remplacer ceci par une gestion appropriée de l'exception
//            return null; // Vous pouvez définir une valeur par défaut ou un autre comportement en cas d'erreur
//        }
//    }
//
//    // Add method to verify token if needed
//}

package com.templateproject.api.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.shaded.gson.Gson;
import com.templateproject.api.utils.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

// Autres imports...

@Service
public class TokenService {

    // Votre code existant...

    private final String jwtSecret;
    private final Gson gson; // Ajoutez une instance de Gson avec l'adaptateur

    @Autowired
    public TokenService(String jwtSecret) {
        this.jwtSecret = jwtSecret;
        this.gson = GsonFactory.createGsonWithInstantAdapter(); // Initialisez Gson avec l'adaptateur
    }
    Instant now = Instant.now();

    public String generateToken(Authentication auth) {
        try {
            Instant expiration = now.plus(30, ChronoUnit.DAYS);

            String scope = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            Jwt jwt = Jwt.withTokenValue("dummy")
                    .header("alg", "HS256")
                    .claim("iss", "self")
                    .claim("sub", auth.getName())
                    .claim("scope", scope)
                    .issuedAt(Date.from(now).toInstant())
                    .expiresAt(Date.from(expiration).toInstant())
                    .build();


            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).build();
            JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jwt.getClaims()));
            JWSSigner signer = new MACSigner(jwtSecret.getBytes());
            jwsObject.sign(signer);

            return jwsObject.serialize();
        } catch (JOSEException e) {
            // Gérer l'exception ici si nécessaire
            e.printStackTrace();
            return null;
        }
    }
}
