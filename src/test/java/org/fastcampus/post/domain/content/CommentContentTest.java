package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenNotThrowError() {
        // given
        String content = "this is a test content";

        // when, then
        assertDoesNotThrow(() -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsOverLimitWhenCreateCommentContentThenThrowError() {
        // given
        String content = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsOverLimitAndKoreanWhenCreateCommentContentThenThrowError() {
        // given
        String content = "테스트".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }



    // 🦊 Empty, Null 테스트 생성 시에는 "NullAndEmptySource" 어노테이션을 이용할 수 있다. 🦊
    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsNullAndEmpty_whenCreateCommentContent_thenThrowError(String value) {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }

//    @Test
//    void givenContentLengthIsEmptyWhenCreateCommentContentThenThrowError() {
//        // given
//        String content = "";
//
//        // when, then
//        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
//    }
//
//    @Test
//    void givenContentLengthIsNullWhenCreateCommentContentThenThrowError() {
//        assertThrows(IllegalArgumentException.class, () -> new CommentContent(null));
//    }

}
