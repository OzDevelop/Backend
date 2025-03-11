package org.fastcampus.post.ui.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/*
응답값을 받는 dto 생성
댓글 리스트, 피드 리스트 두 가지를 불러와야 함.
이 두 리스트의 반환하는 데이터는 거의 유사함.(댓글 개수 여부를 제외하고)

이럴 경우에는 record가 아닌 클래스 상속으로 rombok을 이용하여 쉽게 작성할 수 있음.
 */


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetContentResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private String userName;
    private String userProfileImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private boolean isLikedByMe;
}
