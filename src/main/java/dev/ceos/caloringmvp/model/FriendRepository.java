package dev.ceos.caloringmvp.model;
import dev.ceos.caloringmvp.controller.vo.*;

import java.util.HashMap;
import java.util.List;

public interface FriendRepository {

    FriendResponse findFriend(UserNameVO userNameVO);

    void createFriend(UserTwoVO userTwoVO);


    void deleteFriend(UserTwoVO userTwoVO);


    List<FriendResponse> friends(UserInfoVO userInfoVO);
    //HashMap<dd,FriendResponse> = friends(UserInfoVO userInfoVO);


    Friend findById(long user_id);

    UserTwoVO aleadyCheck(UserTwoVO userTwoVO);
}
