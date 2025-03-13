package org.fastcampus.auth.domain;

import java.security.SecureRandom;

public class RandomTokenGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int TOKEN_LENGTH = 16;

    // java secure 에서 제공하는 랜덤 객체
    private static final SecureRandom random = new SecureRandom();


    private RandomTokenGenerator() {
    }

    public static String generateToken() {

        StringBuilder token = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return token.toString();
    }
}
