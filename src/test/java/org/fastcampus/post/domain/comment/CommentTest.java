package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {
    private final User user = new User(1L, new UserInfo("name", ""));
    private final User otherUser = new User(2L, new UserInfo("name", ""));

    private final PostContent postContent = new PostContent("Content");
    private final Post post = new Post(1L, user,postContent);
    private final Comment comment = new Comment(1L, post, user, "comment content");

    @Test
    void givenCommentCreate_whenLike_thenLikeCountShouldBe1() {


        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());

    }

    @Test
    void givenCommentCreateWhenUnlikeThenLikeCountShouldBe0() {
//        comment.getLikeCount();

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreatedWhenLikeBySameUserThenLikeCountShouldThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreated_whenUpdateContent_thenShouldBeUpdated() {
        // given
        String content = "new content";

        // when
        comment.updateComment(user, content);

        //then
        assertEquals(content, comment.getContent());
    }

}
