package dev.ceos.caloringmvp.model;

import lombok.Data;
import java.sql.Date;
import javax.persistence.*;

@Data
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long user_id;

    @Column
    private String name;
    @Column
    private String password;
    @Column
    private int age;
    @Column
    private short gender;
    @Column
    private Date updateAt;
    @Column
    private int level;
    @Column
    private int attacked_caloring;
    @Column
    private int total_caloring;
    @Column
    private int exercising;

    public User() {}
    public int updateTodayCalor(){
        int pre = total_caloring;
        int penalty=0;

        if(exercising<100) {
            if(attacked_caloring<exercising) {//받은공격량 < 운동량 -> 공격량은 안깎임. 패널티 받음
                total_caloring = total_caloring - (100 - exercising);
                penalty =100-exercising;
            }
            else {//받은공격량 > 내 운동량인 경우 -> 공격량도 깎임. 패널티 받음. 운동량 한번 더 더해줌
                total_caloring = total_caloring - (attacked_caloring + (100 - exercising)) + exercising;
                 penalty= attacked_caloring + (100 - exercising)-exercising;
            }

        }

        // 본인레벨보다 밑 구간 으로 안가게함.
        if(total_caloring < (level-1)*200) {
            total_caloring=(level-1)*200;
            penalty = pre-total_caloring;
        }

        //공격량은 다시 초기화
        attacked_caloring=0;
        //운동량도 다시 초기화
        exercising=0;
        return penalty;
    }

}
