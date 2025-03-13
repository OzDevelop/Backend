package org.fastcampus.acceptance.utils;

import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

// 기본 데이터 생성을 위한 DataLoader 클래스

// User 생성을 위해 스프링을 호출하는 UserAcceptanceSteps 생성

@Component
public class DataLoader {
    /**
     * 강의 시점에는 Part2, Ch2-02 에는 DB에서 토큰을 가지고 오는 로직이 없기 때문에
     * 테스트에서 이런 DB에 저장된 토큰이 잘 있는지 확인하기 위해, entitiyManager 추가 후 getEmailToken 메서드 생성
     */
    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        // user 1, 2, 3 생성 및 user 1 follow user 2, user 3
        CreateUserRequestDto user = new CreateUserRequestDto("user", null);
        createUser(user);
        createUser(user);
        createUser(user);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }

    public boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT isVerified FROM EmailVerificationEntity WHERE email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

}
