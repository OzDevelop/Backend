package org.fastcampus.admin.ui.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fastcampus.common.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableRequestDto extends Pageable {
    private Long postId;
}
