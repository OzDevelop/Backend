package org.fastcampus.auth.repository;

import jakarta.transaction.Transactional;
import java.beans.Transient;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.interfaces.EmailSendRepository;
import org.fastcampus.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.auth.domain.Email;
import org.fastcampus.auth.repository.entity.EmailVerificationEntity;
import org.fastcampus.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;


    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(emailAddress);

        /** 이메일 엔티티가 이미 있다면,
         *  1) 이미 인증이 되어있는지
         *  2) 이미 보냈으나 아직 인증이 안 된 상태
         */
        if (entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();

            if (emailVerificationEntity.isVerified()) {
                throw new IllegalArgumentException("이미 인증된 이메일입니다.");
            }

            emailVerificationEntity.updateToken(token);
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailAddress = email.getEmailText();

        // 저장이 안되어 있으면 예외처리.
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(emailAddress).orElseThrow(() -> new IllegalArgumentException("인증요청하지 않은 이메일입니다."));

        if (entity.isVerified()) {
            throw new IllegalArgumentException("이미 인증된 이메일입니다");
        }

        // 토큰의 일치여부 검증
        if (!entity.hasSameToken(token)) {
            throw new IllegalArgumentException("토큰 값이 유효하지 않습니다..");
        }

        entity.verify();
    }

    @Override
    public boolean isEmailVerified(Email email) {
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(email.getEmailText()).orElseThrow(() -> new IllegalArgumentException("인증요청하지 않은 이메일입니다."));
        return entity.isVerified();
    }
}
