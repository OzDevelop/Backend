package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.springframework.http.MediaType;

public class UserAcceptanceSteps {

    // User 생성을 위한 메서드
    // http 요청을 RestAssured 을 이용해 간단하게 생성해봄.
    public static ExtractableResponse<Response> createUser(CreateUserRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/user")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> followUser(FollowUserRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/relation/follow")
                .then()
                .extract();
    }
}
