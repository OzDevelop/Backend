package org.fastcampus.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.admin.ui.dto.GetTableListResponse;
import org.fastcampus.admin.ui.dto.users.GetUserTableRequestDto;
import org.fastcampus.admin.ui.dto.users.GetUserTableResponseDto;
import org.fastcampus.admin.ui.query.AdminTableQueryRepository;
import org.fastcampus.auth.repository.entity.QUserAuthEntity;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {
    private final JPAQueryFactory queryFactory;

    private static final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity.userEntity;

    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto) {

        int total = queryFactory.select(userEntity.id)
                .from(userEntity)
                .where(likeName(dto.getName()))
                .fetch()
                .size();

        // 현재의 오프셋 상태로는 사용자가 많아 가져올 데이터가 많은 경우 속도가 느림.
        /** 왜 데이터가 많을 때 시간이 더 오래 걸릴까?
         * 현재의 오프셋이 문제임!
         * 50만개의 데이터(예를들어)를 가지고 온 뒤 정렬을 수행하고, 10개씩 오프셋과 리미터를 기준으로 끊어오는 식으로 구성되어 있음.
         * 이는 데이터를 가지고 올 때 PK 클러스터 인덱스에 대한 접근이 같이 많아지게 됨. -> 데이터가 많아지므로 당연히 오래 걸림.
         *
         * 리미터만 채우고 나머지를 안불러오는게 아니라 전체 데이터에서 리미트와 오프셋을 걸기 때문.
         * 강의에서는 3초 후반대가 걸렸는데 실제 서비스에서 이 시간은 상당히 불편하고 긴 시간임.
         *
         * 이를 커버링 인덱스를 이용해 개선해보려 함.
         * Id는 인덱스의 리프노드에 존재하고 있기 때문에 커버링 인덱스가 적용됨.
         * 조건에 맞게 Id를 먼저 조회.
         *
         * 주석은 기존 전체 데이터를 가져오는 코
         */
//        List<GetUserTableResponseDto> result = queryFactory
//                .select(
//                        Projections.fields(
//                                GetUserTableResponseDto.class,
//                                userEntity.id.as("id"),
//                                userAuthEntity.email.as("email"),
//                                userEntity.name.as("name"),
//                                userAuthEntity.role.as("role"),
//                                userEntity.regDt.as("createdAt"),
//                                userEntity.updDt.as("updatedAt"),
//                                userAuthEntity.lastLoginDt.as("lastLoginAt")
//                        )
//                )
//                .from(userEntity)
//                .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
//                .where(likeName(dto.getName()))
//                .orderBy(userEntity.id.desc())
//                // Paging을 위한 offset과 limit
//                .offset(dto.getOffset())
//                .limit(dto.getLimit())
//                .fetch();
//
//        return new GetTableListResponse<>(total, result);


        /**
         * 조건을 가지고 Id를 가지고 오는 값을 만듦.
         * id만 가지고 와서 커버링 인덱스 적용
         * 아래의 ids는  Id 값들만 담김.
         * 우리가 원하는 DTO 들의 파라미터를 넘겨줘서 원하는 id 값만 가지고 있음.
         * 아래의 쿼리는 커버링 인덱스로 기존 50만개(예시)의 데이터를 모두 가져오는 것이 아닌, id만 빠르게 가져옴.
         * -> 데이터가 많아지는 것에 대한 부하가 훨씬 줄어듬.
         *
         */

        List<Long> ids = queryFactory
                .select(userEntity.id)
                .from(userEntity)
                .where(
                        likeName(dto.getName())
                ).orderBy(userEntity.id.desc())
                .offset(dto.getOffset())
                .limit(dto.getLimit())
                .fetch();

        /**
         * 이제 가져온 id 값을(ids)를 이용해  클러스터 인덱스에 접근을 해서 데이터를 가져오면 됨.
         *
         * 커버링 인덱스가 적용 된 id 값을 바탕으로 필요데이터만 불러오는 방식! (3초 후반대에서 1초 중반대로 속도가 빨라짐, 약 3배 개선)
         */

        List<GetUserTableResponseDto> result = queryFactory
                .select(
                        Projections.fields(
                                GetUserTableResponseDto.class,
                                userEntity.id.as("id"),
                                userAuthEntity.email.as("email"),
                                userEntity.name.as("name"),
                                userAuthEntity.role.as("role"),
                                userEntity.regDt.as("createdAt"),
                                userEntity.updDt.as("updatedAt"),
                                userAuthEntity.lastLoginDt.as("lastLoginAt")
                        )
                ).from(userEntity)
                .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
                .where(
                        userEntity.id.in(ids)
                ).orderBy(userEntity.id.desc())
                .fetch();

        return new GetTableListResponse<>(total, result);

    }

    private BooleanExpression likeName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return userEntity.name.like(name + "%");
    }
}

