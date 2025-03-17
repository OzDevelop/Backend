package org.fastcampus.common.Idempotency.Repository;

import java.util.Optional;
import org.fastcampus.common.Idempotency.Repository.entity.IdempotencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity, Long> {
    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
