package dev.ceos.caloringmvp.model;
import dev.ceos.caloringmvp.controller.vo.*;
import dev.ceos.caloringmvp.model.*;

import java.util.List;

public interface AlarmRepository {

    Alarm findByAlarmId(FriendAttackVO friendAttackVO);

    List<AlarmVO> findAlarmList(UserInfoVO userInfoVO);
    //attack 당한 당사자의 id 값이 user_id 에 들어가고, attack 한 사람은 friend_id에 들어감.
    void saveAttackAlarm(FriendAttackVO friendAttackVO);

    void savePenalAlarm(InitVO initVO);

    void saveFriendAlarm(UserTwoVO userTwoVO);

    void saveFriendDelAlarm(UserTwoVO userTwoVO);
}
