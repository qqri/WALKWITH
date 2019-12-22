package dev.ceos.caloringmvp.controller.vo;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
public class UserExerVO {

    public UserExerVO(){ }

    public UserExerVO(long user_id,int exercising,Date updateAt,int level,int total_caloring){
        this.user_id=user_id;
        this.exercising=exercising;
        this.updateAt=updateAt;
        this.level=level;
        this.total_caloring=total_caloring;
    }
    public void updateCaloring(int preLevel)
    {
        /*
        * 누적칼로링 : level
        *   [0~200)   :  1 || 200
        *   [200~400) :  2 || 400
        *   [400~600) :  3 ||
        *   [600~800) :  4
        *   [800~     :  4 이후는 전부 4 까지만  존재
        *   배경이 어두워 지는 경우 : level < total_caloring/200 +1 인데,
        *   패널티/친구 공격으로 깎인 유저가
        *   누적칼로링 250 인데, 레벨은 4인 경우 엔
        *   안깎이게 하는 로직 새로 만들어야 될 예정..
        *   ( total_caloring < (preLevel-1)*200 ) //패널티, 공격으로 깎인 사람은 비가 온다 , 레벨이 증가 하지 않는다.
        *
        *     //exercising/update/1
        *     * 레벨은 줄어 들지 않는다.
        *     * 만약 공격, 패널티를 받은 적이 있는 사람이라면 (줄어든 만큼 + 200) 을 채워야 레벨업이 가능하다.
        * */

        total_caloring += exercising*2;

        if( ((level-1)*200 <= total_caloring ) && level <5) //패널티,공격으로 칼로링 깎인 사람은 레벨 증가 안함.
        {
            level++;
        }
        //test case 1 : total : 500, level: 3, exercising: 60 했을때 level이 4가 되도록
        //          2 : total : 300, level: 3, exercising: 60 했을땐 level 증가 안됨
    }

    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long user_id;
    @Column
    private int exercising;
    @Column
    private Date updateAt;

    @Column
    private int level;
    @Column
    private int total_caloring;



}
