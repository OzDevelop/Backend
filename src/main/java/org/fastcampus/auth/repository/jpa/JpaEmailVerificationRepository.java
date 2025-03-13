package org.fastcampus.auth.repository.jpa;

import java.util.Optional;
import org.fastcampus.auth.repository.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {
    // id값으로는 찾아올 수 없으므로 Optional 처리
    Optional<EmailVerificationEntity> findByEmail(String email);
}
