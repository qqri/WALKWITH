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
            total_caloring = total_caloring - attacked_caloring - (100-exercising);
            if(total_caloring<0)
            { total_caloring=0; }

        }
        //if(){}


        attacked_caloring=0; //초기화
    }
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
