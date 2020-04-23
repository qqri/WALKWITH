package dev.ceos.walkwith.controller;

import dev.ceos.walkwith.controller.vo.*;
import dev.ceos.walkwith.model.*;
import dev.ceos.walkwith.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WalkController {

    public final WalkService walkService;


    @PatchMapping("/exercising/update")
    public ResponseEntity<?> updateExer(@RequestBody UserExerVO userExerVO){
        return new ResponseEntity<>( WalkService.updateExer,HttpStatus.OK);
    }

    @PatchMapping("/exercising/attack")  //user_id = 나 , friend_user_id = 공격할 친구
    public ResponseEntity<?> attackFriend(@RequestBody FriendAttackVO friendAttackVO){

        return new ResponseEntity<>( WalkService.updateExer,HttpStatus.OK);

    }


    @PostMapping("init/today/penalty")
    public ResponseEntity<?> initToday(@RequestBody UserInfoVO userInfoVO) {

        return new ResponseEntity<>( WalkService.initToday , HttpStatus.OK);
    }



}
