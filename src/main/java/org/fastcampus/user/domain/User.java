package org.fastcampus.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
/*
Lombok을 통해 기존 몇 개의 Getter 대체, 원시타입을 반활때만 get을 따로 작성
 */
@Getter
@Builder
@AllArgsConstructor
public class User {

    // id 값을 이용해 객체를 구분하기 위해서는 equal, hashcode 메소드를 overriding해서 구현해야 한다.
    // 오버라이딩하지 않으면 메모리 주소값을 이용해 객체를 동일시 하기 때문.
    private Long id;
    private UserInfo userInfo;
    private PositiveIntegerCounter followingCount;
    private PositiveIntegerCounter followerCount;

    public User (Long id, UserInfo userInfo) {
        if(userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public User(String name, String profileImageUrl) {
        this(null, new UserInfo(name, profileImageUrl));
    }


    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

// 디미터의 법칙을 지키기 위해 분리(쉽게 말하면 .(점) 으로 두번 이상 연달아 호출하지 말자)
    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    public Long getId() {
        return id;
    }

    // control + Enter 단축키를 이용해 생성
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public int followingCount() {
        return followingCount.getCount();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getProfileImage() {
        return userInfo.getProfileImageUrl();
    }

    public String getName() {
        return userInfo.getName();
    }


}
