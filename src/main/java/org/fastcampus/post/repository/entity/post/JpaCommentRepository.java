package org.fastcampus.post.repository.entity.post;

import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {
}
