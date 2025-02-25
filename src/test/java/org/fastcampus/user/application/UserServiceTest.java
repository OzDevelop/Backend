package org.fastcampus.user.application;


/*
🦊 현재 UserRepository 구현체를 만들지 않고 작업을 진행함.
따라서 테스트를 진행하기 위해선 테스트 더블 주입이 필요!!

여기서는 Fake 객체를 통해 인메모리 DB를 만들어 테스트를 진행함.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    //저장한 유저가 정상적으로 반환이 되는지 테스트
    @Test
    void givenUserRequestDto_whenCreateUser_thenCanFindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();

        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());
    }

}
