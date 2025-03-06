package org.fastcampus.user.repository.jpa;

/*
User의 팔로잉, 팔로우 유저 리스트 출력은 페이징에 따라 숫자가 필요할 수도 있고, 팔로워 수가 많다면 인덱싱도 필요함.
이런 경우 조회의 성능을 위해 쿼리리를 직접 작성해야 함.
그래서 이번에는 Spring Data JPA에서 제공하는 JPQL을 사용해 필로잉, 팔로우 리스트를 반환하는 쿼리문 작성.
기존 인터페이스를 재사용하지 않고, 팔로워 팔로잉 리스트 조회를 위한 인터페이스를 따로 생성.
 */

// Usecase를 보게 된다면 이름과 프로필만 필요함.
// 이를 위한 dto class 를 작성, GetUserListResponseDto

import java.util.List;
import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    //JPQL을 사용하기 위한 어노테이션
    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
            + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
            + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);

}

/* 쿼리문이 더욱 복잡해진다면 JPQL로 작성하기 힘듬.
이를 위해 쿼리 DSL 라이브러리를 사용할 수 있음.
쿼리 DSL을 이용하려면 build 파일의 수정 및 Config 값 추가가 필요함.

Query DSL의 특징으로는
    프로젝트가 컴파일될 때 엔티티 어노테이션들을 검색해 새로운 큐 엔티티들을 만듦.
    자동생성된 큐 객체들은 쿼리 DSl 라이브러리에서 쿼리문 작성이 가능하도록 도움.
 */