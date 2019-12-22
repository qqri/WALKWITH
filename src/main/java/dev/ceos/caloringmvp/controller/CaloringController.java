package dev.ceos.caloringmvp.controller;


import dev.ceos.caloringmvp.controller.vo.*;
import dev.ceos.caloringmvp.model.Alarm;
import dev.ceos.caloringmvp.model.AlarmRepository;
import dev.ceos.caloringmvp.model.User;
import dev.ceos.caloringmvp.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CaloringController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AlarmRepository alarmRepository;


    /*
    * 운동량 업데이트 버튼 + 누적칼로링, 운동량 디비 반영 + 증가시 레벨업 됨.
    * requestbody값에 updateAt만 =null 값 넣음
    * level, total_caloring , exercsing 은 내부에 원래 있던 값 받아 와야 한다.
    *
    * 버튼 눌렀을때 > 보내줘야 하는 값 : user_id, [[ exercising ]] 이값이,
    *                                    updateAt, level, total_caloring, **attacked_caloring
    * 버튼 눌렀을때 > 업뎃 돼야 하는 값 : exercising (보내준 만큼), updateAt (그냥 업뎃용),
    *                                     total_caloring(업뎃 됐다면), attacked_caloring(이거도 새로 갖다줘야됨.)
    * */
    @PatchMapping("/exercising/update")
    public ResponseEntity<?> updateExer(@RequestBody UserExerVO userExerVO){

        userExerVO.updateCaloring(userExerVO.getLevel());

        userRepository.updateExer(userExerVO);

        UserExerVO newUserExerVO = userRepository.findById2(userExerVO);

        return new ResponseEntity<>(newUserExerVO,HttpStatus.OK);
    }
    /*
    * 친구 공격 > 친구의 운동량 만큼 공격을 가한다.
    *             나의 attacked_caloring이 증가한다.
    * requestbody 에는 내 id, 친구 아디, 내 운동량, 날짜 넣음 됨
    *
    * 업뎃되는 양은
    * 한번씩만되도록 -> 알람 테이블에 접근해서 확인한다.
    * 받아온 값들을 각각의 레포에다 잘 저장 하면 됨... 인데..
    *
    * */
    @PatchMapping("/exercising/attack")  //user_id = 나 , friend_user_id = 공격할 친구
    public ResponseEntity<?> attackFriend(@RequestBody FriendAttackVO friendAttackVO){

        Alarm check = alarmRepository.findByAlarmId(friendAttackVO);
        if(check != null) //알람이 이미 존재하는지 확인함. > null 이 아니란건 이미 존재한단 거니까 끝내버림
        { return  new ResponseEntity<>(new ResponseVO("warning : already attack"),HttpStatus.OK); }

        long friend_user_id = friendAttackVO.getFriend_user_id();
        User attackCheck = userRepository.findById(friend_user_id);
        if(attackCheck.getAttack_caloring()>150) {
            return new ResponseEntity<>(new ResponseVO("warning : attack caloring over 150"),HttpStatus.OK);
        }

        userRepository.attackFriend(friendAttackVO);
        alarmRepository.saveAttackAlarm(friendAttackVO);
        //alarm 테이블에 event code, my_id, you_id, caloring값, date 값 쌓임.

        return new ResponseEntity<>(new ResponseVO("success : attack success"),HttpStatus.OK);

    }

    /*
    req 값 : id값 s, 전날 ex 값 && 전날 total값 && 전날 attacked 양
    res : ex: 값은 우선은 전날꺼(여기서는 버튼 누르는게 아니니까) / 최종 total   /  attack =0
    * 패널티 생기는 경우 - > 이거 전날 받아온 (100-운동량) 만큼
    * total caloring 깎인
    */
    @PatchMapping("exercising/penalty")
    public ResponseEntity<?> initToday(@RequestBody InitVO initVO){
        //total 값 업뎃, > InitVO
        //userRepo 에서 Init 에 있는 id 값이랑 해서 update 시킴
        initVO.updateTotalCalor();
        userRepository.updateCalor(initVO);
        alarmRepository.savePenalAlarm(initVO);
        return new ResponseEntity<>(new ResponseVO("success : update penalty success"),HttpStatus.OK);
    }



}
