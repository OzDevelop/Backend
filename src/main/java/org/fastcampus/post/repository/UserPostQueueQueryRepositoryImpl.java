package org.fastcampus.post.repository;

import java.util.List;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!test"}) //테스트가 아니기 때문에 프로파일을 이용해 객체 등록이 되지않도록 추가 설정
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {
    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        return List.of();
    }
}
