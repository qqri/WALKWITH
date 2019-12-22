package dev.ceos.caloringmvp.controller;

import dev.ceos.caloringmvp.controller.vo.*;
import dev.ceos.caloringmvp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class FriendController {

    /*
    * 친구검색  - > 검색 결과 없으면 error / 있으면
    * 친구 추가 - > 친구 검색결과 나온 사람 추가한다.  - > 양방향으로 둘다 추가된다
    *
    * 친구 전체 목록 조회 - > 친구 이름, total_caloring , exercising
    *
    *
    * */

    @Autowired
    public FriendRepository friendRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AlarmRepository alarmRepository;


    /*
     * 친구 검색
     * 친구 > 없는 경우 error메세지
     *      > 있는 경우 친구 띄워준다.
     *
     * 도메인/friend/find?name=user1
     */

    @PostMapping("/friend/find")
    public ResponseEntity<?> findFriend(@RequestBody UserNameVO userNameVO) {
        FriendResponse friendOne = friendRepository.findFriend(userNameVO);

        if(friendOne == null) {
            return new ResponseEntity<>(new ResponseVO("warning : friend not exist"),HttpStatus.OK);
        }

        return new ResponseEntity<>(friendOne,HttpStatus.OK);
    }

    /**
     * req body에 내id,친구id
     * 양방향으로 둘다 저장된다!
     *
     */

    @PostMapping("/friend/create")
    public ResponseEntity<?> createFriend(@RequestBody UserTwoVO userTwoVO) {

        UserTwoVO aleadyFriend = friendRepository.aleadyCheck(userTwoVO);
        if(aleadyFriend != null){
            return new ResponseEntity<>(new ResponseVO("warning : aleady friend"),HttpStatus.OK); }
        friendRepository.createFriend(userTwoVO);
        alarmRepository.saveFriendAlarm(userTwoVO);

        return new ResponseEntity<>(new ResponseVO("create friend success"),HttpStatus.OK);
    }


    /**
     * >양방향 삭제
     * 친구 삭제
     */
    @DeleteMapping("/friend/delete")
    public ResponseEntity<?> delecteFriend(@RequestBody UserTwoVO userTwoVO) {

        friendRepository.deleteFriend(userTwoVO);

        return new ResponseEntity<>(new ResponseVO("delete friend success"),HttpStatus.OK);
    }

    /* cdn 오류-해결
     * 전체 친구 조회
     *
     * */

    @PostMapping("/friends") //내유저 아이디 > json 에 담아서 보내기
    public ResponseEntity<?> Friends(@RequestBody UserInfoVO userInfoVO) {
        List<FriendResponse> friendsList = friendRepository.friends(userInfoVO);

        ListResponseVO listResponseVO = new ListResponseVO();
        listResponseVO.setResponse(friendsList);

        return new ResponseEntity<>(listResponseVO,HttpStatus.OK);
    }


}
