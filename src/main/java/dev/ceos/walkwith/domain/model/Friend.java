package dev.ceos.caloringmvp.model;
import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Friend {
    private Long friend_user_id;
    private Long my_user_id;
}
