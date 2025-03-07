package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
