package org.fastcampus.user.domain;

/*
    🦖 VO를 이용해 User 이름을 따로 구현한 이유 🦖
비즈니스 로직인 유효성 검사(이름이 빈 값인지 유무)를 체크해야 함.
이를 위해 VO를 활용해 추후 재사용 가능성을 열어둠.
 */

// User 생성을 위한 유효성 검증
public class UserInfo {
    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
