package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    // 이렇게만 작성해줘도 jpa query method에서 자동으로 이거에 맞는 쿼리문을 작성해줌.
    void deleteAllByUserIdAndAuthorId(Long userId, Long authorId);
}
