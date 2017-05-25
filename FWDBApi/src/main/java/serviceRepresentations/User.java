package serviceRepresentations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO for a game's user.
 */
@AllArgsConstructor
@Getter
@Setter
public class User {
    public Integer id;
    public String name;
    public String userRole;
    public String email;
    public String loginType;
    public Integer level;
    public Integer hintsLeft;
    public Integer goldLeft;
    public String avatarPath;
}