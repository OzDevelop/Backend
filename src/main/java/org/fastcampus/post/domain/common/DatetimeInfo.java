package org.fastcampus.post.domain.common;

import java.time.LocalDateTime;

public class DatetimeInfo {

    private boolean isEdited; // 수정 여부
    private LocalDateTime dateTime; // 수정된 시

    public DatetimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    // 수정이되었다면 수정된 여부와 dateTime 업데이트.
    public void updateEditDatetime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
     }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
