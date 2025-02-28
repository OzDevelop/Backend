package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        // given
        String text = "this is a test";

        // when
        PostContent content = new PostContent(text);

        // then
        assertEquals(text, content.contentText);
    }

//    @Test
//    void givenContentLengthIsOk_whenCreatePostContent_thenNotThrowError() {
//        // given
//        String content = "this is test content";
//
//        // when, then
//        assertDoesNotThrow(() -> new PostContent(content));
//    }

    @Test
    void givenContentLengthIsOverLimit_whenCreatePostContent_thenThrowError() {
        // given
        String content = "A" .repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    // 🦊 ParameterizedTest 어노테이션을 이용해 테스트 고도화 🦊
    // 테스트의 유지보수를 변수 주입 방식으로 변경하여 가독성을 높힐 수 있음.
    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슯"})
    void givenContentLengthIsOverLimitAndKorean_whenCreatePostContent_thenThrowError(String koreanWord) {
        String content = koreanWord.repeat(501);

        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

//    @Test
//    void givenContentLengthIsOverLimitAndKorean_whenCreatePostContent_thenThrowError() {
//        String content = "테".repeat(501);
//
//        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
//    }

    @Test
    void givenContentLengthIsUnderLimit_whenCreatePostContent_thenThrowError() {
        String content = "test";

        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

// 🦊 Empty, Null 테스트 생성 시에는 "NullAndEmptySource" 어노테이션을 이용할 수 있다. 🦊
    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyAndNull_whenCreatePostContent_thenThrowError(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

//    @Test
//    void givenContentLengthIsEmptyWhenCreatePostContentThenThrowError() {
//        String content = "";
//
//        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
//    }
//
//    @Test
//    void givenContentLengthIsNullWhenCreatePostContentThenThrowError() {
//        assertThrows(IllegalArgumentException.class, () -> new PostContent(null));
//    }


}
