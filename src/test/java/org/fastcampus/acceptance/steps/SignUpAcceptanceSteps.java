package org.fastcampus.acceptance.steps;


import io.restassured.RestAssured;
import org.fastcampus.auth.application.dto.CreateUserAuthRequestDto;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.springframework.http.MediaType;

// 1. 이메일을 입력받고, 보내는 부분
public class SignUpAcceptanceSteps {
    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/send-verification-email")
                .then()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer requestVerifyEmail(String email, String token) {
        return RestAssured
                .given()
                .queryParam("email", email)
                .queryParam("token", token)
                .when()
                .get("/signup/verify-token")
                .then()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer registerUser(CreateUserAuthRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/register")
                .then()
                .extract()
                .jsonPath().get("code");
    }

}
