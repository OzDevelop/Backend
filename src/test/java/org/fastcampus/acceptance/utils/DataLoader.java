package org.fastcampus.acceptance.utils;

import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.followUser;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

// 기본 데이터 생성을 위한 DataLoader 클래스

// User 생성을 위해 스프링을 호출하는 UserAcceptanceSteps 생성

@Component
public class DataLoader {
    public void loadData() {
        // user 1, 2, 3 생성 및 user 1 follow user 2, user 3
        CreateUserRequestDto user = new CreateUserRequestDto("user", null);
        createUser(user);
        createUser(user);
        createUser(user);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(2L, 3L));
    }

}
