package org.fastcampus.admin.ui.query;

import java.util.List;
import org.fastcampus.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

public interface UserStateQueryRepository {
    // QueryDSL로 구현체 생성
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserState(int beforeDays);
}
