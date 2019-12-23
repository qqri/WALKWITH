package dev.ceos.caloringmvp.controller.vo;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
public class InitVO {
    public InitVO() { }

    public InitVO(long user_id ,int exercising,int total_caloring,int attacked_caloring,int level)
    {
        this.user_id=user_id;
        this.exercising=exercising;
        this.total_caloring=total_caloring;
        this.attacked_caloring=attacked_caloring;
        this.level = level;
    }

    public void updateTotalCalor(){
        if(exercising<100) {
            if(attacked_caloring<exercising) //받은공격량 < 운동량 -> 공격량은 안깎임. 패널티 받음
                total_caloring = total_caloring -(100-exercising);
            else //받은공격량 > 내 운동량인 경우 -> 공격량도 깎임. 패널티 받음. 운동량 한번 더 더해줌
                total_caloring = total_caloring - (attacked_caloring - (100-exercising)) +exercising;

            if(total_caloring<0) { total_caloring=0; } //레벨1인 유저의 누적 칼로링이 음수가 되지 않도록 해줌.
        }

        // 본인레벨보다 밑 구간 으로 안가게함.
        if(total_caloring < (level-1)*200) total_caloring=level*200;

        //공격량은 다시 초기화
        attacked_caloring=0;
        //운동량도 다시 초기화
        exercising=0;
    }
    /*
    *       다음날 유저가 처음 앱을 켰을때 반영되는 공격량, 패널티에 따른 누적칼로링 변화 test case 입니다.
    *       레벨 2 는 누적칼로링 [200,400) 구간
    *
    * T1 :(80만큼의 패널티를 받지만 본인 레벨구간보단 안떨어짐)
    * (전날) 레벨 / 누적 칼로링 / 운동량 / 공격량(총량) -> (다음날) 레벨 / 누적칼로링 / 운동량 / 공격량
    *         2   /    250      /   20   /   0          ->            2  /    200     /   0    /    0
    *
    *
    * T2 :(20보다 작은 10의 공격량을 받았으로 80만큼의 패널티 받고 T1과 같은 결과)
    *         2   /    250      /   20   /  10          ->            2  /    200     /
    *
    *
    * T3 :()
    *         2   /    250      /   20   /  50          ->           2   /     250- (패널티:100-20) - (공격:50) + (보완?: 20)
    *                                                                        == 250 - 60 ==>> 200 (자기 구간보단 안떨어지니까)
    *
    * */


    @Id
    private long user_id;
    @Column
    private int exercising;
    @Column
    private int total_caloring;
    @Column
    private int attacked_caloring;
    @Column
    private int level;
}
