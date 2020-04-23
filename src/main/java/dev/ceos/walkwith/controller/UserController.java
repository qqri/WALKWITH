package dev.ceos.walkwith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PutMapping(value="/signin")
    public ResponseEntity<?> signIn(@RequestBody User user){
        User check = userRepository.duplicateIdCheck(user);

        if(check != null) {
            return new ResponseEntity<>(new ResponseVO("warning : duplicate id"),HttpStatus.OK);
        }
        return new ResponseEntity<>(UserService.signIn,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody User user) {
        if(UserService.login == null) {
            return new ResponseEntity<>(new ResponseVO("warning : id, pw incrrect"),HttpStatus.OK);
        }
        return new ResponseEntity<>(UserService.login ,HttpStatus.OK);
    }


    @PostMapping("/autoLogin")
    public ResponseEntity<?> autoLogin(@RequestBody UserInfoVO userInfoVO){
        return new ResponseEntity<>( UserService.autoLogin , HttpStatus.OK);
    }

    @PostMapping("/alarm/show")
    public ResponseEntity<?> alarmShow(@RequestBody UserInfoVO userInfoVO){

        return new ResponseEntity<>( UserService.alarmShow , HttpStatus.OK);
    }


}
