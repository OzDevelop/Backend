package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {
    /* 게시물 작성 기능 구현하기 위한, 저장 불러오는 역할을 하는 repository가 필요함.
     postRepository interface를 우선 구현한 후 작성.

     User Service 외에도 Post 객체 생성하기 위한 정보를 받아오는 DTO 객체도 Record로 선언.
     */

    private final UserService userService;

    private final PostRepository postRepository;

    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("post not found"));
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        //  🦖 Post 객체의 일반 생성자를 이용해 post 생성
//        PostContent content = new PostContent(dto.content());
//        Post post = new Post(null, author, content, dto.state());

        //  🦖 Post 객체의 정적 생성자를 이용해 post 생성
        // 또한 데이터 주입이 너무 많아지면 유지보수에 어려움이 있음.
        // 이럴 경우 객체를 더 잘게 나누어 1~2개 정도의 주입만이 가능하도록 쪼개는것도 객체지향적으로 도음이 되는 방법이라 생각함.
        Post post = Post.createPost(null, author, dto.content(), dto.state());

        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());

        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }    }

}













