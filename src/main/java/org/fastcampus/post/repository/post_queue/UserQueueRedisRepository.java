package org.fastcampus.post.repository.post_queue;

import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;

// UserPostQueueCommandRepository의 대용량 데이터에 대한 처리 문제로 개선사항으로 사용할 인터페이스
// 추후에 레디스를 사용할 수 있또록 인터페이스 생성(구현체는 현재 생성하지 않음, Part1, Ch6-05 기준, 인수테스트만 진행 예ㅈ정)
public interface UserQueueRedisRepository {

    // 팔로워 리스트한테 내가 만든 포스트 엔티티를 전달.
    void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList);

    // 내가 새로운 유저를 팔로우 했을 때, 내가 작성한 글을 전달.
    void publishPostListToFollowerUser(List<PostEntity> postEntityList, Long userId);

    // 유저를 언팔로우했을 때, 내 피드에서 글을 삭제
    void deleteDeleteFeed(Long userId, Long authorId);
}
