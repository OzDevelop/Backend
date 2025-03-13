package org.fastcampus.auth.domain;

import java.util.regex.Pattern;

// 이메일 유효성 확인을 위한 클래스
public class Email {

    // email 검증을 위해 정규식 추가
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9_.-]+$";
    // Pattern을 이용해 패턴과 일치여부를 확인.
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;

    private Email(String email) {
        this.emailText = email;
    }

    public static Email createEmail(String email) {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email is not valid");
        }

        if (isNotValidEmailString(email)) {
            throw new IllegalArgumentException("email is not valid");
        }
         
        return new Email(email);
    }

    public String getEmailText() {
        return this.emailText;
    }

    private static boolean isNotValidEmailString(String email) {
        return !pattern.matcher(email).matches();
    }

}
