package org.fastcampus.common.Idempotency.Repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.Idempotency.Idempotency;
import org.fastcampus.common.Idempotency.IdempotencyRepository;
import org.fastcampus.common.Idempotency.Repository.entity.IdempotencyEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        Optional<IdempotencyEntity> idempotencyEntity = jpaIdempotencyRepository.findByIdempotencyKey(key);
        return idempotencyEntity.map(IdempotencyEntity::toIdempotency).orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));

    }
}
