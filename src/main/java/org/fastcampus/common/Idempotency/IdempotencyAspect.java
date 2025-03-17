package org.fastcampus.common.Idempotency;

// 멱등성 객체를 확인하는 AOP 객체

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotencyAspect {
    private final IdempotencyRepository idempotencyRepository;
    private final HttpServletRequest request;

    /**
     * @Around 어노테이션은 특정 로직의 실행과 실행 후, 즉 실행 전과 후의 작업에 관여할 수 있게 해줌.
     * joinPoint.proceed()를 통해 해당 로직을 실행하고, 결과를 반환함.
     * AOP를 통해 메소드가 호출되기 전, 호출된 후 값들을 조정 및 반환될 수 있도록 수정할 예정
     * 멱등키가 이미 처리된 것이라면 반환하고, 아니라면 응답값을 저장하고, 추후에 같은 값이 왔을 때 반환하는 로직을 추가.
     *
    */
    @Around("@annotation(Idempotent)")
    public Object checkIdempotency(ProceedingJoinPoint joinPoint) throws Throwable {
        String idempotencyKey = request.getHeader("Idempotency-Key");
        if (idempotencyKey == null) {
            return joinPoint.proceed();
        }

        Object result = joinPoint.proceed();
        return result;

    }

}
