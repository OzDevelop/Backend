package org.fastcampus.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PageableTest {

    @Test
    void givenPageableIndexIsZero_whenGetOffset_thenShouldBeReturn0() {
        // given
        Pageable pageable = new Pageable();

        // when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        // then
        assertEquals(0, offset);
        assertEquals(10, limit);

    }
}
