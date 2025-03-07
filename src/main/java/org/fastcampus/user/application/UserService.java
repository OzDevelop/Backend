package org.fastcampus.user.application;


// 🦊 UserService는 비즈니스 로직을 처리한다기보단 다른 객체와 협업하는 역할을 주로 수행.

import java.util.IllformedLocaleException;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.GetUserResponseDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.springframework.stereotype.Service;

// 🌱 Spring Container가 UserService를 Bean 객체로 등록할 수 있도 어노테이션 추가 🌱
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create User Service 로직 구현
    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info); // 추후 DB 구현하면 채워서 반환.
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);

        return new GetUserResponseDto(user);
    }

}
