package org.fastcampus.common.principal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 이제 이 어노테이션이 있다면, 헤더에서 인증 토큰을 가지고 와서, 파싱을 해주는 기능 추가해주면 됨.
 * 이를 위해 Spring에서 제공하는 핸들러 메소드, Argument Resolver를 사용할 예정임.
 */

@Target(ElementType.PARAMETER)  // parameter에 붙는 타입임을 명시.
@Retention(RetentionPolicy.RUNTIME) // runtime 시 동작하는 어노테이션임을 명시.
public @interface AuthPrincipal {
}
