package org.fastcampus.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;

public class FakeUserRepository implements UserRepository {
    // 수행과정이 RDB랑 응답이 동일하도록 구성.
    private final Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        // id가 이미 있다면 업데이
        if(user.getId() != null) {
            store.put(user.getId(), user);
        }
        Long id = store.size() + 1L;
        User newuser = new User(id, user.getInfo());

        store.put(id, newuser);
        return newuser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}

