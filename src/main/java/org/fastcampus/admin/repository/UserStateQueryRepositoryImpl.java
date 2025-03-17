package org.fastcampus.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.fastcampus.admin.ui.query.UserStateQueryRepository;
import org.fastcampus.common.TimeCalculator;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStateQueryRepositoryImpl implements UserStateQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    /** 많은 데이터를 사용하는 대시보드를 만들 시 주의 사항.
     * 데이터 범위를 한정적으로 잡아주는 것이 중요.
     * adminController에서 보면 일자를 가지고 7일전 데이터만 사용하여 그룹바이하고있음.
     *  -> where 절에서 참고되는 데이터의 크기를 줄여주는게 중요함!!!!!!!!!!
     */

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserState(int beforeDays) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetDailyRegisterUserResponseDto.class,
                                userEntity.regDate.as("date"),
                                userEntity.count().as("count")
                        )
                )
                .from(userEntity)
                .where(userEntity.regDate.after(TimeCalculator.getDateDaysAgo(beforeDays)))
                .groupBy(userEntity.regDate)
                .orderBy(userEntity.regDate.asc())
                .fetch();

    }
}
