package org.fastcampus.post.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {
    private final UserInfo userInfo = new UserInfo("name", "");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final PostContent postContent = new PostContent("Content");
    private final Post post = new Post(1L, user, postContent);


    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreate_whenUnlike_thenLikeCountShouldBe0() {
        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeBySameUser_thenLikeCountShouldThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlikeByOtherUser_thenLikeCountShouldBe0() {
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        // given
        String newContent = "New Content";

        // when
        post.updatePost(user, newContent, PostPublicationState.PUBLIC);

        // then
        assertEquals(newContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdateOtherUserContent_thenThrowError() {
        // given
        String newContent = "New Content";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newContent, PostPublicationState.PUBLIC));
    }



}
