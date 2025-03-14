package org.fastcampus.auth.repository.jpa;

import java.util.Optional;
import org.fastcampus.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, Long> {
    Optional<UserAuthEntity> findByEmail(String email);


}
