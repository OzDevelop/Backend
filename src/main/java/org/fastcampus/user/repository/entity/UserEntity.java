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
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate // 팔로잉, 팔로워 변경시 전체 업데이트 말고 변경값만 업데이트하기 위해 추가
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

    //🦊 Post와 1:다 관계를 나타내기 위함
    // @OneToMany는 LazyLoading이 default임.
    // 🦊 근데 이거 보다는 Repository를 통해 데이터를 불러오는 방법을 더 많이 사용함.(단방향 매핑) 🦊
        // 만약 fetch를 default(Lazy)가 아닌 EAGER 등으로 설정한다면 포스트에서도 유저 조회가 가능한 양방향 조회가 가능해짐.
        // 이러면 자바 앱 내부적으로 메모리 부족의 문제가 발생할 수 있음.
    // @OneToMany
//    private List<PostEntity> posts;

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
                .userInfo(new UserInfo(name, profileImage))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }
}

