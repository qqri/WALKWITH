package dev.ceos.caloringmvp.model;
import lombok.Data;
import javax.persistence.*;
import java.sql.Date;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Alarm {

    @Id
    @GeneratedValue
    private long friend_user_id;

    @Column
    private long user_id;

    @Column
    private short event;

    @Column
    private int caloring;

    @Column
    private Date updateAt;

    public Alarm(){}
}
