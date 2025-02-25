package org.fastcampus.user.application.dto;


/*
자바 14부터 적용된 내용.
DTO를 만들때 제공해주는 자바 레코드
final로 선언된 불변 변수로만 구성이 되어있, getter() 만 가진 객체들은 레코드 형태로 변경 가능.
 */
public record CreateUserRequestDto(String name, String profileImageUrl) {

}
