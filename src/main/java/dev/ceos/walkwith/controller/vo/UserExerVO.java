package dev.ceos.caloringmvp.controller.vo;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
public class UserExerVO {

    public UserExerVO(){ }

    public UserExerVO(long user_id,int exercising,int attacked_caloring ,int level,int total_caloring){
        this.user_id=user_id;
        this.exercising=exercising;
        this.attacked_caloring=attacked_caloring;
        this.level=level;
        this.total_caloring=total_caloring;
    }
    public void updateCaloring(int preExer)
    {
        /*
        * 누적칼로링 : level
        *   [0~200)   :  1
        *   [200~400) :  2
        *   [400~600) :  3
        *   [600~800) :  4
        *   [800~     :  4 이후는 전부 4 까지만  존재
        *
        *   레벨은 구간에서 벗어날 일 없음.
        *
        * */

        total_caloring += exercising-preExer;

        if( ((level)*200 <= total_caloring) && level <5)
        {
            level++;
        }
        //레벨은 4가 최대이기 때문에 그 이상은 증가안함.
        //300 2 인 유저가 ! 0 50 70 100 운동량 증가시켜서 300 -> 400 돼서 Lev3 되는 case 생각
    }

    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long user_id;
    @Column
    private int exercising;
    @Column
    private int attacked_caloring;

    @Column
    private int level;
    @Column
    private int total_caloring;



}
