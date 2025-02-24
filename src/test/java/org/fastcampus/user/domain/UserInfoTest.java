package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


/* 🦊 객체 내부의 작은 객체를 먼저 테스트하면 얻을 수 있는 이점. 🦊

작은 내부 객체 먼저 검증이 되어야 큰 객체들 테스트 시 빠르게 작성 가능하고,
테스트 중복이 줄어듦.
    -> 정답은 아님.

 */

class UserInfoTest {
    @Test
    void givenNameAndProfileImage_whenCreated_thenThrowNothing() {
        // given
        String name = "abcd";
        String profileImage = "";

        // when
        // then
        assertDoesNotThrow(() -> new UserInfo(name, profileImage));
    }

    @Test
    void givenBlankNameAndProfileImage_whenCreated_thenThrowError() {
        // given
        String name = "";
        String profileImage = "";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImage));
    }

}
