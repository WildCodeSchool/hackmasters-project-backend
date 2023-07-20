package com.templateproject.api.service;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretGenerator {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] secretBytes = new byte[32]; // 32 octets = 256 bits
        random.nextBytes(secretBytes);
        String base64Secret = Base64.getEncoder().encodeToString(secretBytes);
        System.out.println("Generated secret: " + base64Secret);
    }
}
