package org.fastcampus.user.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.application.dto.GetUserResponseDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.jpa.JpaUserListQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    // 유저 생성을 추가하기 전, 응답 값을 통일시켜 반환하면 좋음.
    // 이건 정하기나름(회바회, 팀바팀)이지만 정해져있는 응답 인터페이스 형태를 맞추면 좋음.
    // common - Response(Record)

    private final UserService userService;

    // 조회만 하도록 만든 QueryRepository의 경우 서비스단을 거치지 않고 바로 레파지토리를 의존해 봔환하는 것도 가능 !
    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    // 1차적으로는 생성 시 id 값 반환하도록 구현
    // post 이기 떄문에 requestBody로 생성
    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId")Long userId) {
        return Response.ok(userService.getUserProfile(userId));
    }



    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId")Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId")Long userId) {
        List<GetUserListResponseDto> result = jpaUserListQueryRepository.getFollowerUserList(userId);
        return Response.ok(result);
    }


}
