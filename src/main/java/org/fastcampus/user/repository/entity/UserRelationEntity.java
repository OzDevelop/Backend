package org.fastcampus.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "community_user_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(UserRelationIdEntity.class)
public class UserRelationEntity extends TimeBaseEntity {
    @Id
    private Long followingUserId;

    @Id
    private Long followerUserId;
}

/*
UserRelationEntity는 도메인 객체가 따로 존재하지 않음.
또한 두 유저의 id값을 조합해 관계를 만듬.(유니크함)
이를 위한 방법 중 하나로 RelationIdEntity 객체를 생성하여 관리
이를 PK로 사용

RelationIdEntity를 Id값으로 가짐
 */
