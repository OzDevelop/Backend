package org.fastcampus.post.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }
}
