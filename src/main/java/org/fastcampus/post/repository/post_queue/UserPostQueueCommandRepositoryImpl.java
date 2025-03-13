package org.fastcampus.post.repository.post_queue;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    //🙈 팔로워 피드에 대한 대용량 데이터가 들어올 것은 예상해 문재 해결을 위한 수정
    // 여기를 현재 ㅅ주석된 코드 말고, 새로운 레디스 인터페이스로 변경할 예정
//    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
    // 위 코드를 없애고, 아래의 레디스 레포를 추가
    private final UserQueueRedisRepository redisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        // 1. 글을 쓴 user를 follow하는 UserId를 가져옴.
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersId = jpaUserRelationRepository.findFollowers(userEntity.getId());
        redisRepository.publishPostToFollowingUserList(postEntity, followersId);

        //🙈 같은 이모티콘과 같은 이유로 주석처리, 대신 redisRepository를 이용한 코드로 변경
        // 2. ListQueue 생성
//        List<UserPostQueueEntity> userPostQueueEntityList = followersId.stream()
//                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
//                .toList();
//
//        // 3. Queue 저장
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        redisRepository.publishPostListToFollowerUser(postEntities, userId);
        //🙈 같은 이모티콘과 같은 이유로 주석처리
//        List<UserPostQueueEntity> userPostQueueEntityList = postIdList.stream()
//                .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
//                .toList();
//        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        redisRepository.deleteDeleteFeed(userId, targetId);
        //🙈 같은 이모티콘과 같은 이유로 주석처리
//        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
