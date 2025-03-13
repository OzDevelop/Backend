package org.fastcampus.auth.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.interfaces.EmailSendRepository;
import org.fastcampus.auth.domain.Email;
import org.springframework.stereotype.Repository;

// 원래는 구글 SMTP를 이용한 send를 보내주어야 함.

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {
        // TODO
    }

}
