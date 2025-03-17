package org.fastcampus.admin.ui.dto.users;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fastcampus.common.utils.TimeCalculator;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableResponseDto {
    @Getter
    private Long id;

    @Getter
    private String email;

    @Getter
    private String name;

    @Getter
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    // 시간은 String으로 포매팅 후 전달.
    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }

    public String getLastLoginAt() {
        return TimeCalculator.getFormattedDate(lastLoginAt);
    }
}