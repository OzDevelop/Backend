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
            + "SET p.likeCount = p.likeCount + :likeCount, "
            + "p.updDt = now() "
            + "WHERE p.id = :postId")
    void updateLikeCount(Long postId, Integer likeCount);
    // 아래의 방법은 유실될 가능성이 보여, 위와 같이 수정
//    void updateLikeCount(PostEntity postEntity);

    // MySQL의 락 개념과 같이 보기.
    /*
    MySQL의 경우 유니크한 대상의 insert나 update를 위해 row 한개의 데이터에 락을 검.
    지금은 게시글 1번이라는 유니크한 대상이 있으므로, 1번의 게시글에 대해 락이 걸림.
    동시에 요청이 들어온다고 하면, 먼저 들어온 업데이트 문을 처리하는 동안 다른 업데이트 문은
    데이터에 접근할 수 없이 대기가 걸림.
    그래서 만약 둘 중 먼저 업데이트 된 것이 1로 업데이트 되었다면 거기에 1을 더해 2로 업데이트 될 것임. 그러므로 중복 업데이트 문제를 해결할 수 있음.

    또 다른 해결 방법으로는   1) DB의 isolation 레벨을 serialize로 올리거나,
                        2) 낙관적 락, 비관적 락 을 사용하는 방법이 있음.
                            (락을 이용하는 방법을 성능 이슈를 야기하긴 함;)
    따라서 간단한 쿼리문으로 해결이 가능하면, 쿼리문으로 간단하게(low lock) 해결하는게 좋음.

    이렇듯 동시성 처리같이 DB에서밖에 처리할 수 없는 문제들은 DB에서 처리하는 것이 좋음.

    기존의 like는   해결이 필요한데 어떻게 해야할까? LikeRepositoryImpl 가서 보기

    어떤 부분은 도메인에 비즈니스 로직에서 처리하고, 어떤 부분은 DB repo에서 처리하는게 중요할까?
    자주 변하지 않고, 한번만 처리하면 되는 것은 DB repo, 자주 변하고 복잡한 내용은 도메인에서 하는게 좋아보인다.

    만약 쿠폰에 대해 유효성 검증을 하고 사용여부에 대한 검증이 필요하다고 한다면
    유효성 검증은 도메인 로직에서, 사용 여부는 DB repo에서 처리하는게 좋아 보인다.
     */
    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.commentCount = p.commentCount + 1,"
            + "p.updDt = now()"
            + "WHERE p.id = :id")
    void increaseCommentCount(Long id);

}
