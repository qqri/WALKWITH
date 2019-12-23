package dev.ceos.caloringmvp.controller.vo;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
public class FriendAttackVO {

    public FriendAttackVO(){ }



    public FriendAttackVO(long user_id ,long friend_user_id,int exercising,Date updateAt)
    {
        this.user_id=user_id;
        this.friend_user_id=friend_user_id;
        this.exercising=exercising;
        this.updateAt=updateAt;
    }

    @Id
    private long user_id;
    @Id
    private long friend_user_id;
    @Column
    private int exercising;
    @Column
    private Date updateAt;


}
