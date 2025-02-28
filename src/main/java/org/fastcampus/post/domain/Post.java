package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
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


public class Post {

    private final Long id; // Postid
    private final User author; // Post 작성자
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public Post(Long id, User author, PostContent content) {
        if (author == null) {
            throw  new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
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
}
