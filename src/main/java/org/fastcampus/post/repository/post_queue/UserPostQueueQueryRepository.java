package org.fastcampus.post.repository.post_queue;

import java.util.List;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId );
}
