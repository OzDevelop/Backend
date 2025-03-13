package org.fastcampus.auth.application.interfaces;


import org.fastcampus.auth.domain.Email;

// 저장과, 불러오는 부분이 필요할 듯.
public interface EmailVerificationRepository {
    void createEmailVerification(Email email, String token);
    void verifyEmail(Email email, String token);

}
