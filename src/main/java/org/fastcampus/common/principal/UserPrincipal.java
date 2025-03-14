package org.fastcampus.common.principal;

import lombok.Getter;
import org.fastcampus.auth.domain.UserRole;

@Getter
public class UserPrincipal {
    private Long userid;
    private UserRole userRole;


    public  UserPrincipal(Long userid, String role) {
        this.userid = userid;
        this.userRole = UserRole.valueOf(role);
    }
}
