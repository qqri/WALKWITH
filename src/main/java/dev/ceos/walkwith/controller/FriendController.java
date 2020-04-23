package dev.ceos.walkwith.controller;

import dev.ceos.caloringmvp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FriendController {

    private final FriendService friendService;


    @PostMapping("/friend/find")
    public ResponseEntity<?> findFriend(@RequestBody UserNameVO userNameVO) {
        return new ResponseEntity<>(FriendService.findFriend(userNameVO),HttpStatus.OK);
    }


    @PostMapping("/friend/create")
    public ResponseEntity<?> createFriend(@RequestBody UserTwoVO userTwoVO) {
        return new ResponseEntity<>(FriendService.createFriend(userTwoVO),HttpStatus.OK);
    }


    @DeleteMapping("/friend/delete")
    public ResponseEntity<?> delecteFriend(@RequestBody UserTwoVO userTwoVO) {
        return new ResponseEntity<>(FriendService.deleteFriend(userTwoVO),HttpStatus.OK);
    }

    @PostMapping("/friends") //내유저 아이디 > json 에 담아서 보내기
    public ResponseEntity<?> Friends(@RequestBody UserInfoVO userInfoVO) {
        return new ResponseEntity<>( FriendService.Friends(UserInfoVO) ,HttpStatus.OK);
    }


}
