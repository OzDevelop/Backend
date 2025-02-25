package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    // 한 쌍의 유저의 유효성을 확인하고 넣어야 함.
    // 두 유저의 타겟이 다르기 때문.
    // 테스트 내부에서 사용할 레코드를 넣어 fake 객체 생성
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService,
            userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void setUp() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 =  userService.createUser(dto);
        this.user2 =  userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateToUser_whenFollow_thenUserFollowSaved() {
        // when
        userRelationService.follow(requestDto);

        // then
        // user1이 user2를 follow하는 상황
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        // given
        userRelationService.follow(requestDto);

        // when, then
        assertThrows(IllegalArgumentException.class, () ->userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () ->userRelationService.follow(sameUser));
    }


    // Unfollow
    @Test
    void givenCreateToUserFollow_whenUnfollow_thenUserUnfollowSaved() {
        // given
        userRelationService.follow(requestDto);

        // when
        userRelationService.unfollow(requestDto);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenUserThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () ->userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollowSelf_thenUserThrowError() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () ->userRelationService.unfollow(sameUser));
    }
}
