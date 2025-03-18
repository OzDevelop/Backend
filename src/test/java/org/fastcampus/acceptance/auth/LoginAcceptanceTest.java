package org.fastcampus.acceptance.auth;

import static org.fastcampus.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static org.fastcampus.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.auth.application.dto.LoginRequestDto;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "email@email.com";


    // 매번 새로운 유저를 만들고 등록.
    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "password", "token");

        // when
        String token = requestLoginGetToken(dto);

        // then
        assertNotNull(token);

    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password", "token");

        // when
        Integer code = requestLoginGetResponseCode(dto);

        // then
        assertEquals(500, code);
    }
}
