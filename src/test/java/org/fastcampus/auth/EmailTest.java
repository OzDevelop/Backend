package org.fastcampus.auth;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.auth.domain.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
        // () -> new Email()) 이거랑 Email::new 는 같음, 람다 공부하기
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com", "natty#@naver", "안녕하@세요.com"})
    void givenInvalidEmail_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@ab", "a@naver.com", "natty@naver", "test@test.com"})
    void givenEmailValid_whenCreate_thenReturnEmail(String email) {
        // given

        // when
        Email emailValue = Email.createEmail(email); // 정적 생성자 이용

        // then
        assertEquals(email, emailValue.getEmailText());
    }




}
