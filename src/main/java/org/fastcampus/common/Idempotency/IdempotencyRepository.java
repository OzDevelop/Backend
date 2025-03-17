package org.fastcampus.common.Idempotency;


public interface IdempotencyRepository {
    Idempotency getByKey(String key);
    void save(Idempotency idempotency);
}
