package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import java.util.List;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.http.MediaType;

public class FeedAcceptanceSteps {

    // Post 생성 요청 Step
    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()    // 주어진 결과에 대한 log 추가
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/post")
                .then().log().all()     // 가져온 결과에 대한 log 추가
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }

    // Feed의 정보를 가지고 오는 Step
    public static List<GetPostContentResponseDto> requestFeed(Long userId) {
        return RestAssured
                .given().log().all()    // 주어진 결과에 대한 log 추가
//                .body(dto) // get이기 때문에 body를 지움.
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/feed/{userId}", userId)
                .then().log().all()     // 가져온 결과에 대한 log 추가
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResponseDto.class);
    }
}
