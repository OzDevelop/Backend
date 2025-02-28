package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DatetimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateAreUpdated() {
        // given
        DatetimeInfo dateTimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = dateTimeInfo.getDateTime();

        // when
        dateTimeInfo.updateEditDatetime();

        // then
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(localDateTime, dateTimeInfo.getDateTime());
    }

}