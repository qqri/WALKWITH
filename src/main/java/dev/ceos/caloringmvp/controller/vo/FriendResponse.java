package dev.ceos.caloringmvp.controller.vo;

import lombok.Data;

@Data
public class FriendResponse {
    private long user_id;
    private String name;
    private int exercising;
    private int total_caloring;
    private short level;

    public FriendResponse(long user_id,String name,int exercising,int total_caloring,short level){
        this.user_id=user_id;
        this.name = name;
        this.exercising = exercising;
        this.total_caloring=total_caloring;
        this.level=level;
    }

    public FriendResponse(){ }
}
