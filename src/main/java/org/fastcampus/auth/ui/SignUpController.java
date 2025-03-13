package org.fastcampus.auth.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.EmailService;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.fastcampus.common.ui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailService.sendEmail(dto);
        return Response.ok(null);

        /**
         * send-verification-email는 어떤 동작을 해야할까?
         * 이메일을 보내기 전 토큰을 생성하고, DB에 저장, 그리고 이메일을 전송해야 할듯.
         * 이메일을 보내는건 이번 강의에서 하지 않고, 구글 STMP 라이브러리를 이용해 보낼 수 있음을 참고.
         *
         * 이번에는 인수테스트와 이메일 기능 없이 회원가입을 확인해보는 것은 해보겠음.
         * 이메일 인증을 제한다면, 1)토큰 생성, 2)저장정도를 하면 될듯.
         * 이를 바탕으로 인수테스트 작성
         */
    }

}
