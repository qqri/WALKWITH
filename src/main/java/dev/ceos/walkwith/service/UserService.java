package dev.ceos.walkwith.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.ceos.walkwith.controller.vo.*;
import dev.ceos.walkwith.domain,.model.*;

@RequiredArgsConstructor
@Service

public class UserService {
    /*
     * 회원 아이디 중복 조회하기
     * 로그인 -> 닉네임 , 비번 동시에 쳐서 같은 값이 나와야만 로그인 되도록 한다.
     *           (아이디, 비번에 따라 다르게 하지 않음.)
     * 로그아웃 -> 프론트에서 로컬에 저장된 id값 지우면서 처리한다.
     *
     * */
    @Transactional
    public User signIn(User user) throws DuplicateUserIdException {

        userRepository.signInUser(user);
        User newUser = userRepository.loginCheck(user);

        return newUser;
    }

    @Transactional
    public User login(User user) {
        User loginUser = userRepository.loginCheck(user);
        if(loginUser == null) return null;
        return loginUser;
    }


    @Transactional
    public User autoLogin(UserInfo userInfo) {
        User loginUser = userRepository.loginUser(userInfoVO);
        return loginUser;
    }

    @Transactional
    public ListResponseVO alarmShow( UserInfo userInfo ) {

        List<AlarmVO> alarmList = alarmRepository.findAlarmList(userInfoVO);
        ListResponseVO listResponseVO = new ListResponseVO();
        listResponseVO.setResponse(alarmList);

        return listResponseVO;

    }
}