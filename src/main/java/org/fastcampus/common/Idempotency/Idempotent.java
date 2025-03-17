package org.fastcampus.common.Idempotency;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 멱등성 구현을 위한 어노테이션

/**
 * 애플리케이션 응답 값은  ui 하위에 Response 객체 형태로 있음.
 * 응답값 객체를 키와 함께 가지고 있는 간단한 객체 생성 (Idempotency)
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
}
