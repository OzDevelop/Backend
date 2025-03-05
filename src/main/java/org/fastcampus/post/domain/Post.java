package org.fastcampus.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

/* 🦖 Note.
    1. content 내용을 VO로 빼서 생성할 예정
    2. 여기서 고민해볼 점은 Post에서 User의 객체를 가지고 있을지 long authorId 로 유저 id를 가지고 있을지 고민해봐야함.
        각각의 장단점은 다음과 같음.
        🐙 유저 객체 전체를 참조하고 있다면 ~
        - 유저 객체에 대한 기능이 생겼거나 필요할경 메소드로 바로 사용 가능.
        - 가독성도 좋아짐.
        - 테스트 세팅이 번거로움.
        🐙 Long authorId값을 가지고 있다면 ~
        - 테스트 간편.
        - 생성이 쉬움.
        - 행동 기반의 책임을 생각해보았을때, 객체지향에 가까운 방법은 아님.
 */

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private final Long id; // Postid
    private final User author; // Post 작성자
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public int getLikeCount() {
        return likeCount.getCount();
    }


    /* 🦊 public static createPost 의 형태로 정적 생성자를 만드는 방법. 🦊
    생성하게 된 이유. PostPublicationState를 원래 생성자 값으로 받지 않았는데 수정이 필요해 대책으로 사용.
    PostPublicationState이 포함된 일반 생성자를 만들어도 되지만 왜 정적 생성자로 만들까? (역할은 일반 생성자와 같음, 위 2개, 아래 2개 생성자끼리)

    🦖 정적 생성자를 사용할 때 장점
    메소드를 통해서 어떤 생성자를 사용하는지 한번 더 메소드명으로 알려주기 때문에 유지보수 차원에서 유리함.(이름으로 명확한 구분이 가능)
    일반 생성자를 사용할 경우 파라미터만 다르기 때문에 어떤 생성자를 사용하는지 식별에 불편함이 있음.

    근데 메모리나 생성 위치 측면에서 장단점은 어떻게 될까??
     */
    public static Post createPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefaultPost(Long id, User author, String content) {
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }


    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw  new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like(User user) {
        if(this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updateContent);
    }

    public String getContent() {
        return content.getContentText();
    }
    

    public Content getContentObject() {
        return content;
    }
}
