package org.fastcampus.user.repository.jpa;

import java.util.List;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
    // 나의 팔로워 id만 찾는 메서드
    @Query("SELECT u.followingUserId "
            + "FROM UserRelationEntity u "
            + "WHERE u.followerUserId = :userId")
    List<Long> findFollowers(Long userId);
}
