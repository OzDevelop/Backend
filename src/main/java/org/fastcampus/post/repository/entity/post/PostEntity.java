package org.fastcampus.post.repository.entity.post;


import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.repository.entity.UserEntity;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name="community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Post Entity에서는 User 객체의 참조가 필요함.
    // User와 Post는 1:다 관계이다.
    // @ManyToOne은 EAGER이 default 타입임.
    @ManyToOne
    // UserEntity에서 @OneToMany를 사용하는 대신
    // DDL 생성 시 ForeignKey 생성 제한을 거는 것!!
        // 실무에서도 ForeignKey를 많이 사용하지 않음.
        // 데이터 연관 관계를 강제해주긴 하지만 원치않은 인덱스를 걸고, 제약조건으로 인해 데이터 수정이 어려워짐.
    @JoinColumn(name="author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;
    /* Post Domain에 상태값에 대한 state enum 값이 있음.
    enum String 값과 객체로 변경을 쉽게 해주는 컨버터 어노테이션을 추가해주고, 변환해주는 컨버터 클래스를 추가.
    이럼 이제 데이터가 저장될 때 컨버터를 통해서 데이터를 변환하고, 불러올 땐 state를 반환하는 식으로 구현
    */
    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;
    private Integer likeCount;


    //db에서 관리되는 값이므로 wrapper type이 아닌 primitive 타입으로 선언
    // @ColumnDefault("0") 을 통해 db에서 자동으로 data row 생성 시 값을 0으로 초기화
    @ColumnDefault("0")
    private int commentCount;


    // PostEntity는 Post를 기반으로 생성되어야 하기 때문에 생성자와 빌더를 추가.
    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();

    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .likeCount(new PositiveIntegerCounter(likeCount))
                .build();
    }
}
