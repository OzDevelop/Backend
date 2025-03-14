package org.fastcampus.auth.application;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.auth.application.dto.LoginRequestDto;
import org.fastcampus.auth.application.dto.UserAccessTokenResponseDto;
import org.fastcampus.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.auth.application.interfaces.UserAuthRepository;
import org.fastcampus.auth.domain.Email;
import org.fastcampus.auth.domain.TokenProvider;
import org.fastcampus.auth.domain.UserAuth;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final TokenProvider tokenProvider;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());

        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }

    public UserAccessTokenResponseDto login(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password());
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getUserRole());

        return new UserAccessTokenResponseDto(token);
    }


}
