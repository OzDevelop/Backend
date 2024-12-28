package org.fastcampus.user.domain;

import java.util.Objects;

public class User {

    // id 값을 이용해 객체를 구분하기 위해서는 equal, hashcode 메소드를 overriding해서 구현해야 한다.
    // 오버라이딩하지 않으면 메모리 주소값을 이용해 객체를 동일시 하기 때문.
    private final Long id;
    private final UserInfo info;

    public User (Long id, UserInfo userInfo) {
        this.id = id;
        this.info = userInfo;
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
}
