package org.fastcampus.user.application.interfaces;

import java.util.Optional;
import org.fastcampus.user.domain.User;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
}
