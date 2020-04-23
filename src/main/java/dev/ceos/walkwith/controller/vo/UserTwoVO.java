package dev.ceos.caloringmvp.controller.vo;

import lombok.Data;

import javax.persistence.Id;


@Data
public class UserTwoVO {
    public UserTwoVO(){}
    public UserTwoVO(long user_id, long friend_user_id){
        this.user_id=user_id;
        this.friend_user_id=friend_user_id;
    }
    @Id
    long user_id;
    @Id
    long friend_user_id;

}
