package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate {
        /*
    🦊 PostApplicationTestTemplate을 도입하여 중복 코드 제거 🦊
     */

    @Test
    void givenCreateCommentRequestDto_whenCreate_thenReturnComment() {
        // when
        Comment savedComment = commentService.createComment(commentRequestDto);

        // then
        String content = savedComment.getContent();
        assertEquals(commentContentText, content);
    }

//    @Test
//    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
//        // given
//        Comment savedComment = commentService.createComment(commentRequestDto);
//
//        // when
//        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(savedComment.getId(), user.getId(), "updated-content");
//        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);
//
//        System.out.println(savedComment.getId() + " ///////" + updatedComment.getId() );
//
//        // then
//        assertEquals(savedComment.getId(), updatedComment.getId());
//        assertEquals(savedComment.getAuthor(), updatedComment.getAuthor());
//        assertEquals(savedComment.getContent(), updatedComment.getContent());
//    }

    @Test
    void givenCreatedComment_whenLiked_thenReturnCommentWithLike() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, savedComment.getLikeCount());
    }

//    @Test
//    void givenComment_whenUnlike_thenReturnCommentWithoutLike() {
//        // given
//        Comment savedComment = commentService.createComment(commentRequestDto);
//
//        // when
//        LikeRequestDto likeRequestDto = new LikeRequestDto(savedComment.getId(), otherUser.getId());
//        commentService.likeComment(likeRequestDto);
//        commentService.unlikeComment(likeRequestDto);
//
//        // then
//        assertEquals(0, savedComment.getLikeCount());
//    }
}
