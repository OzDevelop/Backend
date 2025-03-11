package org.fastcampus.post.repository.post_queue;

import org.fastcampus.post.repository.entity.post.PostEntity;

// 현재 인터페이스를 레퍼지토리 하위에 넣은 이유
// 이 인터페이스는 서비스 레이어에 노출되어서는 안되기 때문.

public interface UserPostQueueCommandRepository {
    // 글을 만들었을 때 나를 팔로우하는 유저들에게 글을 넣어주는 메서드
    void publishPost(PostEntity postEntity);

    // 내가 팔로우를 했을 때 팔로우한 사람들의 글을 내 피드로 넣어주는 메소드
    void saveFollowPost(Long userId, Long targetId);

    // 언팔했을 때 언팔한 유저의 글을 피드에서 제거하는 메서드
    void deleteUnfollowPost(Long userId, Long targetId);
}
