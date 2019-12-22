package dev.ceos.caloringmvp.test;

import dev.ceos.caloringmvp.test.Caloring;
import dev.ceos.caloringmvp.test.CaloringRepository;
import dev.ceos.caloringmvp.test.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    public CaloringRepository caloringRepository;

    /**
     * 유저번호 넘어오면, 사용자 정보 넘겨준다.
     * url은 localhost:8080/user?id=1
     *
     * @return
     */
    @GetMapping(value = "/user")
    public Caloring selectOne(@RequestParam long id) {
        //  public User selectOne(@RequestBody Caloring caloring) {
        Caloring caloring = caloringRepository.findById(id);
        return caloring;
    }

    /**
     * 운동량 업데이트
     */
    @PutMapping(value = "/caloring/{id}")
    public ResponseVO updateEx(@RequestBody Caloring caloring) {
        //칼로링 계산
        caloring.calcuateCalor();

        int affected = caloringRepository.updateCalor(caloring);
        return new ResponseVO("OK");
    }

    /*
     * 상대 공격 업데이트
     */
    @PatchMapping(value = "/user/attack")//내 id
    public ResponseVO updateFat(@PathVariable Long id) {//clt에서 받아온값
        Caloring c = caloringRepository.findById(id);//db에서 받아온값
        if (c.getCaloring() >= 100) { //mvp 단계에선 caloring 이 100 만넘으면 //100이하
            Caloring caloring = null;
            if (id == 1) {
                // 상대방 객체를 생성 & 지금 1,2만 있으니까 이렇게한다.
                caloring = caloringRepository.findById((long) 2);
            } else {
                caloring = caloringRepository.findById((long) 1);
            }
            caloringRepository.attack(caloring);
            caloringRepository.reset(c);
        }
        return new ResponseVO("ok");
        //return new ResponseVO("OK");
    }


}
