package org.fastcampus.admin.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.admin.ui.dto.GetTableListResponse;
import org.fastcampus.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableResponseDto;
import org.fastcampus.admin.ui.query.AdminTableQueryRepository;
import org.fastcampus.admin.ui.query.UserStateQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStateQueryRepository userStateQueryRepository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    //메인 페이지
    @GetMapping("/index")
    public ModelAndView index() {
        /** ModelAndView
         * View와 Model 데이터를 함께 담아 전송할 수 있는 컨테이너 역할의 객체
         * setViewName() 으로 반환될 View name 설정
         * View 이름은 View Resolver에 의해 해석되고, 실제 클라이언트에게 전달될 파일 지정.(여기서는 index.html 파일을 전달)
         */
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        List<GetDailyRegisterUserResponseDto> result = userStateQueryRepository.getDailyRegisterUserState(500);
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView users(GetUserTableRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("users");

        GetTableListResponse<GetUserTableResponseDto> result = adminTableQueryRepository.getUserTableData(dto);
        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("userList", result.getTableData());
        modelAndView.addObject("totalCount", result.getTotalCount());

        return modelAndView;
    }



}
