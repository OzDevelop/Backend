package org.fastcampus.auth.application.interfaces;

import org.fastcampus.auth.domain.Email;

// 강의에서 실제 구현체를 만들진 않을 예정
public interface EmailSendRepository {
    void sendEmail(Email email, String token);
}
