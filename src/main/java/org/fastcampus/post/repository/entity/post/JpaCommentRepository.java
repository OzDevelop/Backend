package org.fastcampus.post.repository.entity.post;

import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.content = :#{#comment.getContent()}, "
            + "c.updDt = now()"
            + "WHERE c.id = :#{#comment.getId()}")
    void updateCommentEntity(CommentEntity comment);

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.likeCount = :#{#commentEntity.likeCount},"
            + "c.updDt = now()"
            + "WHERE p.id = :#{#commentEntity.getId()}"
    )
    void updateLikeCount(CommentEntity commentEntity);
}
