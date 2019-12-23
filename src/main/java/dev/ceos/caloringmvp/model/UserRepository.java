package dev.ceos.caloringmvp.model;

import dev.ceos.caloringmvp.controller.vo.InitVO;
import dev.ceos.caloringmvp.controller.vo.*;

public interface UserRepository {

    void signInUser(User user);

    User duplicateIdCheck(User user);



    User findById(Long user_id);

    User findByname(User user);

    String findName2(UserNameVO userNameVO);

    User findByName3(String name);

    UserExerVO findById2(UserExerVO userExerVO);

    InitVO findById3(UserInfoVO userInfoVO);




    User loginCheck(User user);

    void updateExer(UserExerVO userExerVO);

    void attacked(FriendAttackVO friendAttackVO);

    User loginUser(UserInfoVO userInfoVO);

    void updateCalor(InitVO initVO);


}
