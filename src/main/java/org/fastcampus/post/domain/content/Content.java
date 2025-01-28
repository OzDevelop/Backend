package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DatetimeInfo;

/* 🦊 검증이라는 행위를 추상화시키자. 🦊
    행동 기반으로 만든 일을 추상화하여 확장에 유리하도록 함.
    (댓글, 게시글 작성 등에서 동일한 유효성 검증을 반복하기 때문에)
    +) 테스트의 간편함, 메소드 로직 분리

    🐯 SOLID 원칙 중 SRP, OCP원칙이 잘 지켜진 코드 완
 */



public abstract class Content {
    String contentText;
    final DatetimeInfo dateTimeInfo; // 🐶 컴포지션으로 관리

    // 여기서 접근제어자 public 안쓰는 이유
    protected Content(String contentText) {
        checkText(contentText); //생성시 자동 검증 수행
        this.contentText = contentText;
        this.dateTimeInfo = new DatetimeInfo();
    }

    public void updateContent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        this.dateTimeInfo.updateEditDatetime();
    }

    // 여기는 접근제어자 protected로 선언
    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
