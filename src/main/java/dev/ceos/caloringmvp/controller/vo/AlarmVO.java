package dev.ceos.caloringmvp.controller.vo;
import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
public class AlarmVO {


    @Column
    private String name;
    @Column
    private short event;

    @Column
    private int caloring;

    @Column
    private Date updateAt;

    public AlarmVO(){}
}
