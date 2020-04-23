package dev.ceos.caloringmvp.model;
import lombok.Data;
import javax.persistence.*;

@Data
public class Friend {
    private Long friend_user_id;
    private Long my_user_id;
}
