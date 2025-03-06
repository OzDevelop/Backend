package org.fastcampus.user.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

/* DB에 직접적으로 저장하고, 조회하기 위해 Spring Data JPA를 사용해야 함.
그것을 위해 JPA Repository를 추가함.
 */

// User에 대한 생성, 조회 기능 구현 (실제 쿼리 작성없이, JPA의 쿼리 메소드를 이용)
// 간단한 CRUD는 문제가 없지만 복잡한 기능, 조회에 대해서는 사용이 어려움.

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        userEntity = jpaUserRepository.save(userEntity);

        return userEntity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = jpaUserRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return userEntity.toUser();
    }
}
