package dev.ceos.caloringmvp.model;

import lombok.Data;
import java.sql.Date;
import javax.persistence.*;

@Data
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long user_id;

    @Column
    private String name;
    @Column
    private String password;
    @Column
    private int age;
    @Column
    private short gender;
    @Column
    private Date updateAt;
    @Column
    private int level;
    @Column
    private int attack_caloring;
    @Column
    private int total_caloring;
    @Column
    private int exercising;

    public User() {}


}
