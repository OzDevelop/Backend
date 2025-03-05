package org.fastcampus.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
// TimeBaseEntity 상속을 이용해 legDt, updDto를 컬럼으로 추가
public class UserEntity extends TimeBaseEntity {
    /*
  🐥 @Id 어노테이션.
  Entity들이 영속 상태가 되기 위해서는 반드리 id값이 필요함.
  @Id 어노테이션을 통해 아래의 엔티티의 id 칼럼을 설정해준 것.
  🐥 @GeneratedValue(strategy) 어노테이션.
  DB에서 id를 생성해줘, 라는 의미의 어노테이션임.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .info(new UserInfo(name, profileImage))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }
}

