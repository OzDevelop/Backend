package org.fastcampus.auth.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class TokenProvider {

    /** JWT 기능을 구현하기 위해서는 Secret Key가 필요함.
     * hash 값을 여러 값을 넣어 하나하나 대조해 그 결과를 반환하고, 그 결과값들을 조합해 비밀번호를 유추하는 것을 막기위해 스크릿 키를 솔트로 사용.
     * 시크릿 키를 아래와 같이 JWT 라이브러리에서 제공함.
     */
    private final SecretKey key;
    private static final long TOKEN_VALID_TIME = 1000L * 60 * 60; // 1 hour

    public TokenProvider(String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes()); // secretkey 값은 256비트 이상이 들어가야 안전함. 그 이하로 들어갈경우 예외처리되어 에러 반환.
    }

    public String createToken(Long userId, String role) {
        Claims claims = Jwts.claims()
                .subject(userId.toString())
                .build();

        Date now = new Date();
        Date validateDate = new Date(now.getTime() + TOKEN_VALID_TIME );

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validateDate)
                .claim("role", role)
                .signWith(key)
                .compact();
    }

    public Long getUserId(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject()
        );
    }

    public String getUserRole(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

}
