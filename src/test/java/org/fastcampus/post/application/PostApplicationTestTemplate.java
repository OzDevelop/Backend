package org.fastcampus.post.application;

import org.fastcampus.Fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

    /*
    PostServiceTest와 CommentServiceTest는 거의 동일한 테스트를 수행하므로,
    각 Service,Dto 등 필요 객체가 중복됨.
    이를 해결하기 위해 PostServiceTest와 CommentServiceTest의 중복 코드를 제거하고,
    🦊 공통 부분을 추상화하여 상속하는 방법을 사용.(TestTemplate) 🦊

     */

public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test Content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String commentContentText = "this is test comment";
    CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is test comment");
}
