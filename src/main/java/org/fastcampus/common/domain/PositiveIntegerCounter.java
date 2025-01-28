package org.fastcampus.common.domain;

/*
user domain의 UserRelationCounter을 공통 객체로 변경.
양수의 범위에서 숫자 증감은 user에서 팔로워, 팔로잉수나 comment에서 좋아요 증감의 로직이 동일하기 떄문.

 🦖 공통의 객체를 만들때의 장단점
    -
 */

public class PositiveIntegerCounter {
    private int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (count <= 0) {
            return;
        }
        this.count--;
    }
}
