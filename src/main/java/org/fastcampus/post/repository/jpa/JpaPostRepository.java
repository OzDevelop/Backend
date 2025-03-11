package org.fastcampus.post.repository.jpa;

import static org.fastcampus.post.repository.entity.post.QPostEntity.postEntity;

import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    // 내가 팔로우하는 유저가 작성한 글의 id를 모두 가져오는 메서드
    @Query("SELECT p.id "
            + "FROM PostEntity p "
            + "WHERE p.author.id = :authorId")
    List<Long> findAllPostIdsByAuthorId(Long authorId);


    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.content = :#{#postEntity.getContent()}, "
            + "p.state = :#{#postEntity.getState()},"
            + "p.updDt = now() "
            + "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.likeCount = :#{#postEntity.likeCount}, "
            + "p.updDt = now() "
            + "WHERE p.id = :#{#postEntity.getId()}")
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.commentCount = p.commentCount + 1,"
            + "p.updDt = now()"
            + "WHERE p.id = :id")
    void increaseCommentCount(Long id);

}
