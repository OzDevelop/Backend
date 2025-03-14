package org.fastcampus.auth.domain;

public class TokenProvider {

    public String createToken(Long userId, String rule) {
        return "";
    }

    public Long getUserId(String token) {
        return -1L;
    }

    public String getUserRole(String token) {
        return "";
    }

}
