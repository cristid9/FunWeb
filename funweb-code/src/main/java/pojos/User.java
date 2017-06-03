package pojos;

/**
 * Created by Marius on 6/3/2017.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO for a game's user.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    public Long id;
    public String name;
    public String userRole;
    public String email;
    public String loginType;
    public Integer level;
    public Integer hintsLeft;
    public Integer goldLeft;
    public String avatarPath;
}