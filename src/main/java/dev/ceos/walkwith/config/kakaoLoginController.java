package dev.ceos.caloringmvp.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.ceos.caloringmvp.config.ResponseTokenVO;
import dev.ceos.caloringmvp.model.User;
import dev.ceos.caloringmvp.model.UserRepository;
import dev.ceos.caloringmvp.config.util.KakaoAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class kakaoLoginController {

    @Autowired
    private KakaoAPI kakao;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/")
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/login")
    public ResponseEntity login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakao.getAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);

        String kakaoEmail = (String) userInfo.get("email");
        Long kakaoId = (Long) userInfo.get("id");
        String firstYN = "N";

        System.out.println("user_id : " + kakaoId);
        System.out.println("user_email : " + kakaoEmail);
        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록

        if (userInfo.get("email") == null) {
            return null;
        }

        session.setAttribute("userId", kakaoEmail);  // 세션에 담고
        session.setAttribute("access_Token", access_Token);
        // 유저 테이블에 저장
        User user = userRepository.findById(kakaoId);
        if (user == null) {//true 값이 아니면
            //userRepository.register(new User(kakaoId, kakaoEmail));
            firstYN = "Y";
        }
        //인터셉터 > 디코드

        // 토큰 생성해서 리턴
        if (session.getAttribute("userId") == null) {
            return null;
        }

        Algorithm algorithm = Algorithm.HMAC256("secret");
        Map<String, Object> headerClaims = new HashMap();
        headerClaims.put("userId", userInfo.get("email"));

        String jwt = JWT.create()
                .withHeader(headerClaims)
                .withClaim("user", "1234")
                .sign(algorithm);
        //.withExpiresAt(new Date())

        return ResponseEntity.ok(new ResponseTokenVO(jwt, firstYN));


    }


    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        kakao.kakaoLogout((String) session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("userId");
        session.invalidate();
        return "index";
    }


}