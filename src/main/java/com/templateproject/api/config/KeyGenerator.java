package com.templateproject.api.config;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class KeyGenerator {
    private static final int DEFAULT_KEY_LENGTH = 32; // 256 bits

    public static String generateSecretKey(int keyLength) {
        byte[] key = generateRandomBytes(keyLength);
        return Base64.getEncoder().encodeToString(key);
    }

    public static String generateSecretKey() {
        return generateSecretKey(DEFAULT_KEY_LENGTH);
    }

    private static byte[] generateRandomBytes(int length) {
        byte[] bytes = new byte[length];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }
}
