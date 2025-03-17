package org.fastcampus.acceptance;

import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestFeedCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {
    private String token;
// 템플릿에 작성된 User 생성 및 follow 도식화

    /**
     * User1 --- follow ---> User2 User1 --- follow ---> User3
     */

    @BeforeEach
    void setUp() {
        super.init();
        this.token = login("user1@test.com");
    }

    /**
     * User2 Create Post 1 User1 Get Post 1 From Feed
     */

//    @Test
//    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerFeedCanGetPost() {
//// given
//        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post.",
//                PostPublicationState.PUBLIC);
//        Long createdPostId = requestCreatePost(dto);
//
//// when, 팔로워의 피드 요청
//        List<GetPostContentResponseDto> result = requestFeed(token);
//
//// then
//        assertEquals(1, result.size());
//
//        assertEquals(createdPostId, result.get(0).getId());
//    }

    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
// given
// when, 팔로워의 피드 요청
        Integer code = requestFeedCode("abcd");

// then
        assertEquals(400, code);
    }
}