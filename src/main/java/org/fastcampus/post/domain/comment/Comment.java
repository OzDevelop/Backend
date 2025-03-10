package org.fastcampus.post.domain.comment;

import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.user.domain.User;

@Getter
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

//    public Comment(long id, User author, Content contentObject) {
//    }

    // 정적 생성자 사용 (CommentService에서 사용함)
//    public static Comment createComment(Post post, User author, String content) {
//        return new Comment(null, post, author, new CommentContent(content));
//    }

    public Comment(Long id, Post post, User author, Content content) {
        this(id, post, author, content, new PositiveIntegerCounter());
    }

    public Comment(Long id, Post post, User author, String content) {
        this(id, post, author, new CommentContent(content), new PositiveIntegerCounter());
    }

    @Builder
    public Comment(Long id, Post post, User author, Content content, PositiveIntegerCounter likeCount) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (post == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = likeCount;
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

    public void updateComment(User user, String updatedContent) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException();
        }
        this.content.updateContent(updatedContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    public Content getContentObject() {
        return content;
    }
}
