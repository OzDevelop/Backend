package org.fastcampus.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.auth.domain.TokenProvider;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    // key는 256비트 이상이 되어야 하기 때문
    private final String secretKey = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    // 토큰 생성
    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() {
        // given
        Long userId = 1L;
        String role = "ADMIN";

        // when
        String token = tokenProvider.createToken(userId, role);

        // then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }

    @Test
    void givenInvalidToken_whenGetUserId_thenThrowError() {
        // given
        String invalidToken = "invalid_token";

        assertThrows(Exception.class, () -> tokenProvider.getUserId(invalidToken));
    }

}
