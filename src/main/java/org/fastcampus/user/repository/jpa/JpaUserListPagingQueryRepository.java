package org.fastcampus.user.repository.jpa;

// 기존 JPQL User List는 유저가 몇만, 몇천만명이든 한번에 가져옴. + 다이나믹 쿼리의 작성이 어려움
// QueryDSL을 사용해 아이디 순으로 100명씩 잘라서 가져오는 아이디 기반 페이징 기능을 추가 해 기능 구체화 시도.

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.fastcampus.user.repository.entity.QUserRelationEntity;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    //쿼리 DSL을 사용하기 위해 JPA Query Factory를 가져옴.
    private final JPAQueryFactory queryFactory;

    // Q 객체들을 불러와야 함.
    // 유저 관계에 따른 유저 정보를 가져와야 하기 때문에 두 개의 Q 객체를 가져옴.
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;
    private final JPAQueryFactory jpaQueryFactory;


    // ??????????? 공부해보자
    public List<GetUserListResponseDto> getFollowerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetUserListResponseDto.class
                        )
                )
                .from(relation)
                .join(user).on(relation.followingUserId.eq(user.id))
                .where(
                        relation.followerUserId.eq(userId)
                        ,hasLastData(lastFollowerId)
                )
                .orderBy(user.id.desc())
                .limit(20)
                .fetch();

    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return user.id.lt(lastId);
    }

}
