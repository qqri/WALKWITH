package dev.ceos.caloringmvp.controller;

import dev.ceos.caloringmvp.controller.vo.*;
import dev.ceos.caloringmvp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    /*
    * 회원 아이디 중복 조회하기
    * 로그인 -> 닉네임 , 비번 동시에 쳐서 같은 값이 나와야만 로그인 되도록 한다.
    *           (아이디, 비번에 따라 다르게 하지 않음.)
    * 로그아웃 -> 프론트에서 로컬에 저장된 id값 지우면서 처리한다.
    *
    * */
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public AlarmRepository alarmRepository;

    /*
     * 회원가입 처음시 값 저장
     * id(일련번호),닉네임, 비번, 나이, 성별, 레벨=1
     * 공격 칼로링, 총칼로링, null값
     *
     * */

    @PutMapping(value="/signin")
    public ResponseEntity<?> signIn(@RequestBody User user){
        User check = userRepository.duplicateIdCheck(user);

        if(check != null) {
            return new ResponseEntity<>(new ResponseVO("warning : duplicate id"),HttpStatus.OK);
        }

        userRepository.signInUser(user);
        //long newId = user.getUser_id();
        //User newUser = userRepository.findById(newId);
        User newUser = userRepository.loginCheck(user);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    /*
    * 유저가 정보 입력함 -> 아이디, 비번 입력
    * 아이디하고 비번 같은지 확인 함
    * */
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody User user){
        User loginUser = userRepository.loginCheck(user);

        if(loginUser == null) {
            return new ResponseEntity<>(new ResponseVO("warning : id, pw incrrect"),HttpStatus.OK);
        }
        return new ResponseEntity<>(loginUser,HttpStatus.OK);
    }

    @PostMapping("/autoLogin")
    public ResponseEntity<?> autoLogin(@RequestBody UserInfoVO userInfoVO){
        User loginUser = userRepository.loginUser(userInfoVO);

        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    @PostMapping("/alarm/show")
    public ResponseEntity<?> alarmShow(@RequestBody UserInfoVO userInfoVO){

        List<AlarmVO> alarmList = alarmRepository.findAlarmList(userInfoVO);


        ListResponseVO listResponseVO = new ListResponseVO();
        listResponseVO.setResponse(alarmList);

        return new ResponseEntity<>(listResponseVO, HttpStatus.OK);
    }


}
