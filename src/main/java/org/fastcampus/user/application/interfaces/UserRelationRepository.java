package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

public interface UserRelationRepository {

    // User id를 이용하지 않고, User객체 전체를 넘기는 이유는 뭘까?
    /*
     User의 count도 같이 업데이트되어야하기 때문.
     트랜잭션 단위로 변경이 일어나야 하는 것들은 같이 메소드로 묶어주면 유지보수가 쉬움.
     만약 userid만 줬다면 select를 한번 더 하는 등 추가 작업이 일어날 수 있음.
     */

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);

}
