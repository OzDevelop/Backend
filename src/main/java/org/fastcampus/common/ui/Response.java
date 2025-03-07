package org.fastcampus.common.ui;

import org.fastcampus.common.domain.exception.ErrorCode;

// 어떤 응답이 올지 모르므로 제네릭 타입으로 생성
public record Response<T>(Integer code, String message, T value) {

    // 추가적으로 exception별로 핸들링이 가능하도록 Global exception Handler 로 추가하자.
    // 항상 같은 형태의 response가 내려갈 수 있도록 함 .
    // 그 전에 에러를 정의한 에러코드 enum도 추가해서 같이 내려주자.

    public static <T> Response<T> ok(T value) {
        return new Response<>(0, "ok", value);
    }


    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }





}
