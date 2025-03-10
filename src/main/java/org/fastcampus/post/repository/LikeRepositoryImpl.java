package org.fastcampus.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.entity.like.LikeEntity;
import org.fastcampus.post.repository.entity.post.JpaCommentRepository;
import org.fastcampus.post.repository.entity.post.JpaLikeRepository;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Repository;

/*
like 엔티티는 생성, 삭제밖에 없음.
또한 like 엔티티는 항상 지정해둔 3개의 id로 동작을 함.
하지만 spring data jpa에서 save의 경우 merge가 발생함.
여기서 항상 저장 전 있는지 조회 후 저장하는 일이 발생함(쿼리가 더 많이 수행됨)
따라서 항상 insert만 발생할 수 있도록(조회 없이) @PersistenceContext 어노테이션 추가 및
엔티티 매니저를 직접 추가해서 entitiy 관리를 통해 불필요한 조회 없이 바로 저장할 수 있음.

엔티티 매니저를 통해 persist를 진행할 때 만약 같은 id가 있다면??(물론 확인 후 저장하지만)
    db에서 primary key는 하나밖에 가질 수 없음.
    따라서 중복 id가 들어간다면 duplicate error가 뜸.


@Transactional -> 변경 감지
@Transactional이 있다면 변경 감지가 일어나고, 자동으로 저장되는 게 Spring Data jpa인데,
매번 postEntity, commentEntity를 매번 업데이트하는게 번거롭다고 생각할 수 있음.

왜 우리가 Entity를 나누고, @Transactional 변경 감지도 못하게 만들었는지 생각해볼 필요가 있음.
    PostService의 likePost를 한번 보자.
    만약 Post Domain에 객체를 저장했다고 생각해보면 Post에 Entity가 들어가 있는 셈임.
    그렇게 된다면 굳이 post를 매번 저장하고, 업데이트문을 작성하지 않아도 like count 증가 등의 로직들이 모두 내부에 있으므로 그냥 사용하면 됨.
    (Service Layer에서 @Transactional으로 수행)
    하지만 그러면 두 가지  단점이 있음.
    1. Controller의 기능을 추가함에 따라 Service의 코드가 수정되어야 함.
        -> Entity를 따로 분리하였기에 Service, Domain 단에서의 코드 수정이 없음.
    2. DB 로직이 수정되면, Service 레이어도 영향을 받는다는 문제!!!

 */

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager em;

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId() );
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        em.persist(likeEntity);
//        jpaLikeRepository.save(likeEntity);
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    // deleteById 대신 delete를 사용하면 안되나?
    @Override
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);

        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        em.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }

    @Override
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));

    }
}
