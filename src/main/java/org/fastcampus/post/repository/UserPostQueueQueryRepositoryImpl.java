package org.fastcampus.post.repository;

import java.util.List;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {
    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        return List.of();
    }
}
