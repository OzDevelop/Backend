package org.fastcampus.common.Idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fastcampus.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency {

    private final String key; // 멱등키
    private final Response<?> response;

}
