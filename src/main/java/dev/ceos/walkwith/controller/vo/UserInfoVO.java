package dev.ceos.caloringmvp.controller.vo;
import lombok.Data;
import java.sql.Date;
import javax.persistence.*;

@Data
public class UserInfoVO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long user_id;

    public UserInfoVO() {}

    public UserInfoVO(long user_id){
        this.user_id=user_id;
    }
}
