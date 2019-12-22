package dev.ceos.caloringmvp.controller.vo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Data
public class UserNameVO {
    @Id
    private String name;

    public UserNameVO() {}

    public UserNameVO(String name){
        this.name=name;
    }

}

