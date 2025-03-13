package org.fastcampus.auth.domain;

// 이메일 유효성 확인을 위한 클래스
public class Email {

    private final String emailText;

    private Email(String email) {
        this.emailText = email;
    }

    public static Email createEmail(String email) {
        return new Email(email);
    }

    public String getEmailText() {
        return this.emailText;
    }

}
