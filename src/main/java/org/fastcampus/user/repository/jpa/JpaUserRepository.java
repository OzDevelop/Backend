package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
