package org.fastcampus.post.repository;

import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Repository
@Profile({"!test"}) //테스트가 아니기 때문에 프로파일을 이용해 객체 등록이 되지않도록 추가 설정
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {

    }

    @Override
    public void publishPostListToFollowerUser(List<PostEntity> postEntityList, Long userId) {

    }

    @Override
    public void deleteDeleteFeed(Long userId, Long authorId) {

    }
}
