package org.fastcampus.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    //조회가 아닌 부분은 @Transactional 이 필요함
    // updateCommentEntity 내부에 @Modifying응 사용할 때는 @Transactional이 필요하다고 생각하면 됨.
    @Override
    @Transactional
    public Comment save(Comment comment) {
        // 댓글 작성 로직에 코멘트가 작성되면 코멘트를 올리는 로직을 추가
        // 하지만 현재 Post를 불러온다 해도, PostEntity로 분리되어 있기 떄문에 단순 저장으로는 Post의 CommentCount가 증가하지 않음
        // 이를 해결하기 위해 JpaPostRepo에 값을 증가시킬 수 있는 JPQL 쿼리문을 추가
        Post targetPost = comment.getPost();

        /*
        1. likeCount는 Domain에서 값을 증가시키는 로직이 있고,
        2. commentCount는 Entity(JpaPostRepository)에 값을 증가시키는 로직이 있음

        이 두 방식의 장단점은 뭘까?
        1 은 역할을 객체에 위임하고, 유연하게 사용할 수 있다는 장점이 있음.
        2 는 repository 기능만으로 따로 만들지 않고 기능 구현이 가능했음.

         */

        CommentEntity commentEntity = new CommentEntity(comment);
        if (comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return comment;
        }

        commentEntity = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(targetPost.getId());
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }
}
