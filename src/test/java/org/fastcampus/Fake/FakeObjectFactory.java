package org.fastcampus.Fake;

/* FakeObjectFactory를 만드는 이유.
매 테스트를 만들때 마다 많은 값들을 주입받고 생성하고 만들려면 테스트가 복잡해지고,
서비스 인자 변경 시 모두가 변경되어야 하는 문제가 발생함.

이럴 경우(의존성 주입이 많아지는 경우) 테스트 시 FakeObjectFactory를 Singleton으로 만들어 문제를 해결할 수 있음.
 */

import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakeLikeRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationRepository = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    // 싱글톤으로 사용할꺼라 외부에서 FakeObjectFactory를 생성하지 못하게 private 생성자로 막음.
    private FakeObjectFactory() {}
}
