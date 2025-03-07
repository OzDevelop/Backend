package org.fastcampus.user.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
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

    // 1차적으로는 생성 시 id 값 반환하도록 구현
    // post 이기 떄문에 requestBody로 생성
    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());

    }
}
