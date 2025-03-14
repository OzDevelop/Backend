package org.fastcampus.auth.application.interfaces;

import org.fastcampus.auth.domain.UserAuth;
import org.fastcampus.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth auth, User user );
    UserAuth loginUser(String email, String passwrod);
}
