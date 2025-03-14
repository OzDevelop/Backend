package org.fastcampus.auth.domain;

/**
 * 여기서는 세가지 스트림 값을 받아 한번 메쉬업을 해주는 클래스
 * 유저 이메일, 패스워드, 권한을 묶어주는 클래스
 */

public class UserAuth {
    private final Email email;
    private final Password password;
    private final UserRole userRole;
    private Long userId;

    public UserAuth(String email, String password, String role) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptPassword(password);
        this.userRole = UserRole.valueOf(role);
    }

    public UserAuth(String email, String password, String role, Long userId) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptPassword(password);
        this.userRole = UserRole.valueOf(role);
        this.userId = userId;
    }

    public String getEmail() {
        return email.getEmailText();
    }


    public String getPassword() {
        return password.getEncryptedPassword();
    }

    public String getUserRole() {
        return userRole.name();
    }

    public Long getUserId() {
        return userId;
    }
}
