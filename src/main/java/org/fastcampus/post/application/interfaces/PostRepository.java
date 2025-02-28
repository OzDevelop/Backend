package org.fastcampus.post.application.interfaces;

import java.util.Optional;
import org.fastcampus.post.domain.Post;

// PostService에 주입 예정.
public interface PostRepository {
    Post save(Post post);

    Optional<Post> findById(Long id);
}
